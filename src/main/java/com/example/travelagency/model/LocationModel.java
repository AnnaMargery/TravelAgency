package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String continent;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;

    //todo dodać relacje z Adress

}
