package com.example.travelagency.model;

import com.example.travelagency.service.TripService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Getter
@Setter
@ToString
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Long duration;
    private Integer numberOfPlaces;
    private boolean isPromoted;
    private Double priceForAdult;
    private Double priceForChild;
    @Enumerated(EnumType.STRING)
    private FoodOption foodOption;
    @ManyToOne
    private AirportModel airportFrom;
    @ManyToOne
    private AirportModel airportTo;
    @ManyToOne
    private HotelModel hotel;



}
