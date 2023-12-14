package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.AirportModel;
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

    public Set<String> getAirportModelNames() {
        List<AirportModel> allAirports = airportRepository.findAll();
        Set<String> airports = new HashSet<>();
        for (AirportModel airport : allAirports) {
            airports.add(airport.getName());
        }
        return airports;
    }

    public AirportModel addAirport(AirportModel airportToAdd, AddressModel airportAddress) {
        if (!airportRepository.existsById(airportToAdd.getId())) {
            airportToAdd.setAddress(airportAddress);
        }
        return airportRepository.save(airportToAdd);
    }

    public void deleteAirport(Long airportId) {
        if (!airportRepository.existsById(airportId)) {
            airportRepository.deleteById(airportId);
        }
        throw new ApiRequestException("Airport not found for id: " + airportId);
    }

    public List<AirportModel> getAirportList() {
        if (!airportRepository.findAll().isEmpty()) {
            return airportRepository.findAll();
        }
        throw new ApiRequestException("Airport list is empty");
    }

    public AirportModel saveEditAirport(AirportModel airportToEdit) {
        if (airportRepository.existsById(airportToEdit.getId())) {
            airportRepository.save(airportToEdit);
        }
        throw new ApiRequestException("Airport not found for id: " + airportToEdit.getId());
    }

    public AirportModel getAirportById(Long airportId) {
        if (airportRepository.existsById(airportId)) {
            airportRepository.findById(airportId);
        }
        throw new ApiRequestException("Airport not found for id: " + airportId);
    }

}
