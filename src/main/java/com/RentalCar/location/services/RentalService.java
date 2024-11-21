package com.RentalCar.location.services;

import com.RentalCar.location.model.Rental;
import com.RentalCar.location.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
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
