package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class PriceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double priceForAdult;
    private Double priceForChildren;
    @OneToOne
    private FoodModel food;
    @ManyToMany
    private Set<TripModel> trip;



}
