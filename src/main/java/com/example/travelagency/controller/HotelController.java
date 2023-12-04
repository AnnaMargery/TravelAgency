package com.example.travelagency.controller;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.repository.HotelRepository;
import com.example.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelModel>> getAllHotelsList(){
        try {
            List<HotelModel> hotels = hotelService.getHotelsList();
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<HotelModel>> getHotelsByContinent(@PathVariable(value = "continent") String continent){
        try {
            List<HotelModel> hotelsByContinent = hotelService.getHotelsByContinent(continent);
            return ResponseEntity.ok(hotelsByContinent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/standard/{standardRate}")
    public ResponseEntity<List<HotelModel>> getHotelsByStandard(@PathVariable(value = "standardRate") Integer standardRate){
        try {
            List<HotelModel> hotelsByContinent = hotelService.getHotelsByStandard(standardRate);
            return ResponseEntity.ok(hotelsByContinent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //todo metody edit, delete, add
}
