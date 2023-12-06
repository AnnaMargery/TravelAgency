package com.example.travelagency.controller;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AddressModel;
import com.example.travelagency.service.AddressService;
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


    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAddressList() {
        try {
            List<AddressModel> addressList = addressService.getAddressList();
            return ResponseEntity.ok(addressList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("continent/{continent}")
    public ResponseEntity<List<AddressModel>> getAddressesByContinent(@PathVariable String continent) {
        List<AddressModel> addressByContinent = addressService.findAddressesByContinent(continent);
        return ResponseEntity.ok(addressByContinent);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long addressId) {
        try {
            AddressModel addressByContinent = addressService.findAddressById(addressId);
            return ResponseEntity.ok(addressByContinent);
        } catch (ApiRequestException e) {
            throw new ApiRequestException("Address not found for id: " + addressId);
        }
    }

    @PostMapping()
    public ResponseEntity<AddressModel> addAddress(@RequestBody AddressModel addressToAdd, Long locationId) {
        AddressModel address = addressService.addAddress(addressToAdd, locationId);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("addressToUpdate")
    public ResponseEntity<AddressModel> saveEditAddress(@RequestBody AddressModel addressToUpdate){
        AddressModel updatedAddress = addressService.updateAddress(addressToUpdate);
        return ResponseEntity.ok(updatedAddress);
    }

    //todo zrobić testy w Postmanie!
}
