package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer duration;
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
