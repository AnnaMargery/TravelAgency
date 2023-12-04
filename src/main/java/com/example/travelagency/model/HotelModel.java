package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
