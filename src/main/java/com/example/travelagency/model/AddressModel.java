package com.example.travelagency.model;

import jakarta.persistence.*;

@Entity
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String postalCode;
    @ManyToOne
    private LocationModel location;


}
