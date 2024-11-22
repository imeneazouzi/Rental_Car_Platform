package com.RentalCar.location.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private int year;
    private double pricePerDay;
    private boolean available;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;


}
