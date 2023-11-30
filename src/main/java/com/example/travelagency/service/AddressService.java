package com.example.travelagency.service;

import com.example.travelagency.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //TODO IMPLEMENTACJA METOD CRUD ZASTANOWIC SIE JAKIE METODY JESZCZE BEDA POTRZEBNE

}


