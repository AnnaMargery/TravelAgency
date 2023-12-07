package com.example.travelagency.controller;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<HotelModel>> getAllHotelsList() {
        List<HotelModel> hotels = hotelService.getHotelsList();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelModel> getHotelById(@PathVariable(value = "hotelId") Long hotelId) {
        HotelModel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<HotelModel>> getHotelsByContinent(@PathVariable(value = "continent") String continent) {
        List<HotelModel> hotelsByContinent = hotelService.getHotelsByContinent(continent);
        return ResponseEntity.ok(hotelsByContinent);
    }

    @GetMapping("/standard/{standardRate}")
    public ResponseEntity<List<HotelModel>> getHotelsByStandard(@PathVariable(value = "standardRate") Integer standardRate) {
        List<HotelModel> hotelsByContinent = hotelService.getHotelsByStandard(standardRate);
        return ResponseEntity.ok(hotelsByContinent);
    }

    @PostMapping()
    public ResponseEntity<HotelModel> addHotel(@RequestBody HotelModel hotelToAdd, AddressModel hotelAddress) {
        HotelModel hotel = hotelService.addHotel(hotelToAdd, hotelAddress);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<HotelModel> saveEditHotel(@RequestBody HotelModel hotelToUpdate) {
        HotelModel updatedHotel = hotelService.saveEditHotel(hotelToUpdate);
        return ResponseEntity.ok(updatedHotel);
    }
}
