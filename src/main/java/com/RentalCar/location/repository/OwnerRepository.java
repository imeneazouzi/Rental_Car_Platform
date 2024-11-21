package com.RentalCar.location.repository;

import com.RentalCar.location.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findClientById(Long id);

    Owner findByEmail(String email);
}