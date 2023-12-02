package com.example.travelagency.model;

import jakarta.persistence.*;

@Entity
public class PriceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double priceForAdult;
    private Double priceForChildren;
    @OneToOne
    private FoodModel food;


    //TODO relacjaTrip&Price
//    @ManyToOne
//    private TripModel tripModel;
    @OneToOne(mappedBy = "price")
    private TripModel tripModel;





}
