package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long duration;
    private Integer numberOfPlaces;
    private boolean isPromoted;
    private Double priceForAdult;
    private Double priceForChild;
    @Enumerated(EnumType.STRING)
    private FoodOption foodOption;
    @ManyToOne(cascade = CascadeType.ALL)
    private AirportModel airportFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    private AirportModel airportTo;
    @ManyToOne(cascade = CascadeType.ALL)
    private HotelModel hotel;


//    public void getDuration() {
//        Long durationInMillies = Math.abs(this.endDate.getTime() - this.startDate.getTime());
//        Long durationInDays = TimeUnit.DAYS.convert(durationInMillies, TimeUnit.MILLISECONDS);
//        this.duration = durationInDays;
//    }
//    public void setDuration(Long duration) {
//        this.duration = duration;
//        getDuration();
//    }

}
