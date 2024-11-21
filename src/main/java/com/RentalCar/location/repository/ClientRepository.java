package com.RentalCar.location.repository;

import com.RentalCar.location.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientById(Long id);

    Client findByEmail(String email);
}