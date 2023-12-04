package com.example.travelagency.service;

import com.example.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

// todo wyszukiwanie hotelu po id, po lokalizacji, po standardzie

}
