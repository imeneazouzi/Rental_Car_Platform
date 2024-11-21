package com.RentalCar.location.controller;

import com.RentalCar.location.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RentalCar.location.services.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {


    private CarService carService;

    public CarController(CarService carservice) {
        this.carService = carservice;
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

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        return ResponseEntity.ok(carService.updateCar(id, carDetails));
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
}
