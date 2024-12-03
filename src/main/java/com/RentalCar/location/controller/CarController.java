package com.RentalCar.location.controller;

import com.RentalCar.location.model.Car;
import com.RentalCar.location.model.Owner;
import com.RentalCar.location.repository.CarRepository;
import com.RentalCar.location.repository.OwnerRepository;
import com.RentalCar.location.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RentalCar.location.services.CarService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {


    private CarService carService;
    private CarRepository carRepository;
    private OwnerRepository ownerRepository;
    @Autowired
    private ImageService imageService;

    public CarController(CarService carservice,CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carService = carservice;
        this.carRepository=carRepository;
        this.ownerRepository=ownerRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{carId}")
    public Car updateCar(@PathVariable Long carId, @RequestBody Car carDetails) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Owner owner = ownerRepository.findById(carDetails.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        existingCar.setModel(carDetails.getModel());
        existingCar.setBrand(carDetails.getBrand());
        existingCar.setYear(carDetails.getYear());
        existingCar.setPricePerDay(carDetails.getPricePerDay());
        existingCar.setAvailable(carDetails.isAvailable());
        existingCar.setOwner(owner);

        return carRepository.save(existingCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCars() {
        carService.deleteAllCars();
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/availability")
    public ResponseEntity<String> updateAvailability(@PathVariable Long id, @RequestParam boolean available) {
        carService.updateCarAvailability(id, available);
        return ResponseEntity.ok("Car availability updated");
    }
    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }


    @PostMapping("/{carId}/upload-image")
    public ResponseEntity<String> uploadCarImage(@PathVariable Long carId, @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.saveImage(carId, file);
            carService.updateCarImage(carId, imageUrl);
            return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }

}
