package com.RentalCar.location.repository;

import com.RentalCar.location.model.Client;
import com.RentalCar.location.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClient(Client client);
}
