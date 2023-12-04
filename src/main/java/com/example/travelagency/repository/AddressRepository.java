package com.example.travelagency.repository;

import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {


    List<AddressModel> findAllByPostalCode(String postalCode);

    List<AddressModel> findAllByStreet(String streetName);

    List<AddressModel> findAllByLocationContinentContains(String continent);



}
