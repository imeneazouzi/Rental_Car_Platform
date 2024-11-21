package com.RentalCar.location.controller;

import com.RentalCar.location.model.Owner;
import com.RentalCar.location.services.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.addOwner(owner);
        return ResponseEntity.ok(savedOwner);
    }

    @GetMapping("/")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        return ResponseEntity.ok(ownerService.updateOwner(id, ownerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return ResponseEntity.noContent().build();
    }
}
