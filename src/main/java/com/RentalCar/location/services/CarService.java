package com.RentalCar.location.services;

import com.RentalCar.location.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.RentalCar.location.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Transactional
    public Car updateCar(Long id, Car carDetails) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + id));
        existingCar.setBrand(carDetails.getBrand());
        existingCar.setModel(carDetails.getModel());
        existingCar.setYear(carDetails.getYear());
        existingCar.setPricePerDay(carDetails.getPricePerDay());
        existingCar.setOwner(carDetails.getOwner());
        return carRepository.save(existingCar);
    }

    public void deleteCarById(Long id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with ID: " + id);
        }
        carRepository.deleteById(id);
    }
    public void updateCarAvailability(Long carId, boolean available) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + carId));
        car.setAvailable(available);
        carRepository.save(car);
    }
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }



    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
