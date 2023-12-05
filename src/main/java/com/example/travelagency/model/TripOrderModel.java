package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class TripOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TripModel trip;

    @OneToMany(mappedBy = "tripOrder")
    private Set<TripParticipantModel> participant;

    private Integer numberOfAdults;
    private Integer numberOfChildren;

    @ManyToOne
    private FoodModel foodOption;

    //private Double totalPrice;

}
