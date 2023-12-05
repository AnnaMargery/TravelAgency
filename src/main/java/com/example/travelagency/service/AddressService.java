package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.LocationModel;
import com.example.travelagency.repository.AddressRepository;
import com.example.travelagency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final LocationService locationService;

    @Autowired
    public AddressService(AddressRepository addressRepository, LocationRepository locationRepository1, LocationService locationService) {
        this.addressRepository = addressRepository;
        this.locationService = locationService;
    }

    public List<AddressModel> getAddressList() {
        return addressRepository.findAll();
    }

    public AddressModel findAddressById(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new ApiRequestException("Address not found for id: " + addressId);
        }
        return addressRepository.findById(addressId).get();
    }

    public AddressModel addAddress(AddressModel addressToAdd, Long locationId) {
        LocationModel location = locationService.findById(locationId);
        addressToAdd.setLocation(location);
        return addressRepository.save(addressToAdd);
    }

    public List<AddressModel> findAddressesByContinent(String continent) {
        if (!addressRepository.findAllByLocationContinentContains(continent).isEmpty()) {
            return addressRepository.findAllByLocationContinentContains(continent);
        }
        throw new ApiRequestException("Address not found for Continent: " + continent);
    }

    public void deleteAddress(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new ApiRequestException("Address not found for id: " + addressId);
        }
        locationService.deleteLocation(addressId);
    }

    public AddressModel updateAddress(AddressModel addressToUpdate) {
        if (!addressRepository.existsById(addressToUpdate.getId())) {
            throw new ApiRequestException("Address not found for id: " + addressToUpdate.getId());
        }
        return addressRepository.save(addressToUpdate);
    }
}

