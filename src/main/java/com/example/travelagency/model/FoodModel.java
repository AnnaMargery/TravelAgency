package com.example.travelagency.model;

import jakarta.persistence.*;

@Entity
public class FoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodOption;

}
