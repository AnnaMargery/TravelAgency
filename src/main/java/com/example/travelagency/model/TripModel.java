package com.example.travelagency.model;

import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.service.TripService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;


//@EntityListeners({TripService.class})
@Entity
@Getter
@Setter
@ToString
public class TripModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
    private Date startDate;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
    private Date endDate;
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
