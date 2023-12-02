package com.example.travelagency.model;

import jakarta.persistence.*;

@Entity
public class HotelModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer standard;
    private String description;
    @OneToOne
    private AddressModel address;



}
