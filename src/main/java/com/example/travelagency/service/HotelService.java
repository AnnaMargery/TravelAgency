package com.example.travelagency.service;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AddressModel;
import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelModel> getHotelsList() {
        if (!hotelRepository.findAll().isEmpty()) {
            return hotelRepository.findAll();
        }
        throw new ApiRequestException("Hotel list is empty");
    }

    public HotelModel getHotelById(Long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            return hotelRepository.findById(hotelId).get();
        }
        throw new ApiRequestException("Hotel not found for id: " + hotelId);
    }

    public List<HotelModel> getHotelsByContinent(String continent) {
        if (!hotelRepository.getAllByAddress_LocationContinent(continent).isEmpty()) {
            return hotelRepository.getAllByAddress_LocationContinent(continent);
        }
        throw new ApiRequestException("Hotels not found for continent: " + continent);
    }

    public List<HotelModel> getHotelsByStandard(Integer standard) {
        if (!hotelRepository.getHotelModelByStandard(standard).isEmpty()) {
            return hotelRepository.getHotelModelByStandard(standard);
        }
        throw new ApiRequestException("Hotels not found for standard: " + standard);
    }

    public HotelModel addHotel(HotelModel hotelToAdd, AddressModel addressOfHotel) {
        if (!hotelRepository.existsById(hotelToAdd.getId())) {
            hotelToAdd.setAddress(addressOfHotel);
        }
        return hotelRepository.save(hotelToAdd);
    }

    public void deleteHotel(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
        }
        throw new ApiRequestException("Hotel not found for id: " + hotelId);
    }

    public HotelModel saveEditHotel(HotelModel hotelToEdit) {
        if (hotelRepository.existsById(hotelToEdit.getId())) {
            hotelRepository.save(hotelToEdit);
        }
        throw new ApiRequestException("Hotel not found for id: " + hotelToEdit.getId());
    }

}
