package com.example.travelagency.controller;

import com.example.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }



}
