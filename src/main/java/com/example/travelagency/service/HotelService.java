package com.example.travelagency.service;

import com.example.travelagency.exception.ApiExceptionHandler;
import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.repository.AddressRepository;
import com.example.travelagency.repository.HotelRepository;
import com.example.travelagency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, LocationRepository locationRepository, AddressRepository addressRepository) {
        this.hotelRepository = hotelRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    public List<HotelModel> getHotelsList(){
        return hotelRepository.findAll();
    }

    public HotelModel getHotelById(Long hotelId){
        return hotelRepository.findById(hotelId).orElseThrow();
    }

     public List<HotelModel> getHotelsByStandard(Integer standard){
        return hotelRepository.getHotelModelByStandard(standard);
     }

     public List<HotelModel> getHotelsByContinent(String continent){
         return hotelRepository.getAllByAddress_LocationContinent(continent);
     }

// todo wyszukiwanie hotelu po id, po lokalizacji, po standardzie

}
