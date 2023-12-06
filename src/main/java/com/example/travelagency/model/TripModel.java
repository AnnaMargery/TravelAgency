package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

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
    @ManyToOne
    private AirportModel airportFrom;
    @ManyToOne
    private AirportModel airportTo;
    @ManyToOne
    private HotelModel hotel;
    @ManyToMany(mappedBy = "trip")
    private Set<PriceModel> price;


}
