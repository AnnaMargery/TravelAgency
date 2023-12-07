package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@ToString
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private Integer numberOfPlaces;
    private boolean isPromoted;
    private Double priceForAdult;
    private Double priceForChild;
    private String foodOption;
    @ManyToOne
    private AirportModel airportFrom;
    @ManyToOne
    private AirportModel airportTo;
    @ManyToOne
    private HotelModel hotel;



}
