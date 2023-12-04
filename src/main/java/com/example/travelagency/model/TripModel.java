package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
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
    @ManyToMany(mappedBy = "trip")
    private Set<PriceModel> price;


}
