package com.RentalCar.location.services;

import com.RentalCar.location.model.Owner;
import com.RentalCar.location.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner updateOwner(Long id, Owner ownerDetails) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + id));
        existingOwner.setFirstName(ownerDetails.getFirstName());
        existingOwner.setEmail(ownerDetails.getEmail());
        return ownerRepository.save(existingOwner);
    }


    public void deleteOwnerById(Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Owner not found with id " + id);
        }
    }
}
