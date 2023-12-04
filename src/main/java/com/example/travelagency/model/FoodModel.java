package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodOption;

}
