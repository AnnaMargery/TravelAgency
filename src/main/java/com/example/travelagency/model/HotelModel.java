package com.example.travelagency.model;

import jakarta.persistence.*;

@Entity
public class HotelModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer standard;
    @OneToOne
    private AddressModel address;
// todo przemyslec opis obiektu hotelowego jako dodatkowe pole


}
