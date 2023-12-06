package com.example.travelagency.service;

import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.LocationModel;
import com.example.travelagency.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportModel> getAirportList(){
        return airportRepository.findAll();
    }

    public Set<String> getAirportModelNames(){
        List<AirportModel> allAirports = airportRepository.findAll();
        Set<String> airports = new HashSet<>();
        for (AirportModel airport : allAirports) {
            airports.add(airport.getName());
        }
        return airports;
    }


}
