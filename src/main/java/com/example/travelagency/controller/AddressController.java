package com.example.travelagency.controller;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.service.AddressService;
import com.example.travelagency.service.LocationService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Builder
@RestController
@RequestMapping(value = "/address", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {

    private final AddressService addressService;
    private final LocationService locationService;

    @Autowired
    public AddressController(AddressService addressService, LocationService locationService) {
        this.addressService = addressService;
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAddressList() {
        try {
            List<AddressModel> addressList = addressService.getAddressList();
            return ResponseEntity.ok(addressList);
        } catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("continent/{continent}")
    public ResponseEntity<List<AddressModel>> getAddressesByContinent(@PathVariable String continent) {
        try {
            List<AddressModel> addressByContinent = addressService.findAddressByContinent(continent);
            return ResponseEntity.ok(addressByContinent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<AddressModel> addAddress(@RequestBody AddressModel addressToAdd, Long locationId) {
        try {
            AddressModel address = addressService.addAddress(addressToAdd, locationId);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//TODO zaimplemnetowac metody delete/ i edit/ find by id?
}
