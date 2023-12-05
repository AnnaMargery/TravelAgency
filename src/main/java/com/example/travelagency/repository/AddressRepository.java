package com.example.travelagency.repository;

import com.example.travelagency.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {


    List<AddressModel> findAllByLocationContinentContains(String continent);



}
