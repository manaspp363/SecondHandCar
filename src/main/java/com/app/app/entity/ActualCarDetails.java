package com.app.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actual_car_details")
public class ActualCarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "trim")
    private String trim;

    @Column(name = "manufactured_year")
    private String manufactured_year;

    @Column(name = "release_year")
    private String releaseYear;

    private String purchaseYear;

    @Column(name = "km_driven")
    private String km_driven;

}