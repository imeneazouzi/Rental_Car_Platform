package com.RentalCar.location.repository;

import com.RentalCar.location.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findCarById(Long id);

    List<Car> findByBrand(String brand);
    List<Car> findByAvailableTrue();

}
