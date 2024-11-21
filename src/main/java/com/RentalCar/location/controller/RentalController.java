package com.RentalCar.location.controller;

import com.RentalCar.location.model.Rental;
import com.RentalCar.location.services.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rental) {
        Rental savedRental = rentalService.addRental(rental);
        return ResponseEntity.ok(savedRental);
    }

    @GetMapping("/")
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rentalDetails) {
        return ResponseEntity.ok(rentalService.updateRental(id, rentalDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalById(@PathVariable Long id) {
        rentalService.deleteRentalById(id);
        return ResponseEntity.noContent().build();
    }
}
