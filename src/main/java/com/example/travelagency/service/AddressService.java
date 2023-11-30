package com.example.travelagency.service;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressModel addAddress(AddressModel addressToAdd) {
        return addressRepository.save(addressToAdd);
    }

    public List<AddressModel> getAddressList() {
        return addressRepository.findAll();
    }

    //TODO IMPLEMENTACJA METOD CRUD ZASTANOWIC SIE JAKIE METODY JESZCZE BEDA POTRZEBNE

}


