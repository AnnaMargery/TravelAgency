package com.example.travelagency.repository;

import com.example.travelagency.model.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel,Long> {

    List<HotelModel> getAllByAddress_LocationContinent(String continent);
    List<HotelModel> getHotelModelByStandard(Integer standardRate);





}
