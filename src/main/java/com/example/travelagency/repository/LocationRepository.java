package com.example.travelagency.repository;

import com.example.travelagency.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Long> {

    List<LocationModel> findAllLocationsByContinent(String continent);

    List<LocationModel> findAllLocationsByCountry(String country);


    LocationModel findLocationByCity(String city);

    void deleteAddressById(Long addressId);


}
