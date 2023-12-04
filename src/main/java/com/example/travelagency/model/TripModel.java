package com.example.travelagency.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private Integer numberOfPlaces;
    private boolean isPromoted;
    @OneToOne
    private AirportModel airportFrom;
    @OneToOne
    private AirportModel airportTo;
    @OneToOne
    private HotelModel hotel;
    @OneToOne
    private PriceModel price;


}
