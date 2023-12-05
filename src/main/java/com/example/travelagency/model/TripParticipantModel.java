package com.example.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class TripParticipantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TripOrderModel tripOrder;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
