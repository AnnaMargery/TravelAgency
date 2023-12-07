package com.example.travelagency.repository;

import com.example.travelagency.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {


    List<AddressModel> findAllByLocationContinentContains(String continent);



}
