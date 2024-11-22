package com.RentalCar.location.services;

import com.RentalCar.location.model.Car;
import com.RentalCar.location.model.Rental;
import com.RentalCar.location.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import com.RentalCar.location.repository.CarRepository;

@Service
@Transactional
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository,CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository= carRepository;

    }

    public Rental addRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public List<Rental> getRentalsByClientId(Long clientId) {
        return rentalRepository.findByClientId(clientId);
    }
    @Transactional
    public Rental createRental(Rental rental) {
        Car car = rental.getCar();
        if (!car.isAvailable()) {
            throw new IllegalStateException("Car is not available for rental");
        }
        car.setAvailable(false);
        carRepository.save(car);

        return rentalRepository.save(rental);
    }

    @Transactional
    public void endRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found with id: " + rentalId));

        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);

        rentalRepository.delete(rental); // Ou autre logique de fin
    }


    public Rental updateRental(Long id, Rental rentalDetails) {
        Rental existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + id));
        existingRental.setStartDate(rentalDetails.getStartDate());
        existingRental.setEndDate(rentalDetails.getEndDate());
        existingRental.setTotalPrice(rentalDetails.getTotalPrice());
        existingRental.setCompleted(rentalDetails.isCompleted());
        return rentalRepository.save(existingRental);
    }


    public void deleteRentalById(Long id) {
        if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rental not found with id " + id);
        }
    }
}
