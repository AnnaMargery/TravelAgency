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
    private FoodModel foodId;




}
