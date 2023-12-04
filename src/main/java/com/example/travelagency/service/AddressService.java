package com.example.travelagency.service;

import com.example.travelagency.exception.ApiExceptionHandler;
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
    private final LocationRepository locationRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, LocationRepository locationRepository, LocationRepository locationRepository1) {
        this.addressRepository = addressRepository;
        this.locationRepository = locationRepository1;
    }

    public List<AddressModel> getAddressList() {
        return addressRepository.findAll();
    }

    public AddressModel findAddressById(Long addressId) throws ApiExceptionHandler {
        return addressRepository.findById(addressId).orElseThrow(ApiExceptionHandler::new);
    }

    public AddressModel addAddress(AddressModel addressToAdd, Long locationId) throws ApiExceptionHandler {
        LocationModel locationModel = locationRepository.findById(locationId).orElseThrow(ApiExceptionHandler::new);
        addressToAdd.setLocation(locationModel);
        return addressRepository.save(addressToAdd);
    }


    public List<AddressModel> findAddressByContinent(String continent) {
        List<AddressModel> allAddresses = addressRepository.findAll();
        List<AddressModel> addressByContinent = addressRepository.findAll();

        for (AddressModel address : allAddresses) {
            if(address.getLocation().getContinent().equals(continent)){
                addressByContinent.add(address);
            }
        }
        return addressByContinent;
    }

    public void deleteAddress(Long addressId) throws ApiRequestException {
        if (!addressRepository.existsById(addressId)) {
            throw new ApiRequestException("Address not found" + addressId);
        }
        locationRepository.deleteAddressById(addressId);
    }

    public AddressModel updateLocation(AddressModel addressToUpdate) throws ApiRequestException {
        if(!addressRepository.existsById(addressToUpdate.getId())){
            throw new ApiRequestException("Address not found" + addressToUpdate.getId());
        }
        return addressRepository.save(addressToUpdate);
    }


}



