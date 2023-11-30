package com.example.travelagency.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer Duration;
    private Integer numberOfPlaces;
    private boolean isPromoted;
    @OneToOne
    private AirportModel airportFrom;
    @OneToOne
    private AirportModel airportTo;
    @OneToOne
    private HotelModel hotel;
    @OneToMany(mappedBy = "tripModel")
    private Set<PriceModel> price;





}
