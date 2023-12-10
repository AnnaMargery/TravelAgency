package com.example.travelagency.controller;

import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final LocationService locationService;
    private final AirportService airportService;
    private final HotelService hotelService;
    private final TripOrderService tripOrderService;

    @Autowired
    public TripController(TripService tripService, LocationService locationService, AirportService airportService, HotelService hotelService, TripOrderService tripOrderService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.airportService = airportService;
        this.hotelService = hotelService;
        this.tripOrderService = tripOrderService;
    }

    @GetMapping()
    public String getTripList(Model model) {
        List<TripModel> tripList = tripService.getTripList();
        model.addAttribute("trips", tripList);
        return "trips";
    }

    @GetMapping("continent/{continent}")
    public String getTripListByContinent(@PathVariable(value = "continent") String continent, Model model) {
        List<TripModel> tripsByContinent = tripService.getTripsByContinent(continent);
        model.addAttribute("tripsByContinent", tripsByContinent);
//        return "redirect:/trips/continent/{continent}";
        return "tripsByContinent";
    }

    @GetMapping("country/{country}")
    public String getTripListByCountry(@PathVariable(value = "country") String country, Model model) {
        List<TripModel> tripsByCountry = tripService.getTripsByCountry(country);
        model.addAttribute("tripsByCountry", tripsByCountry);
//        return "redirect:/trips/country/{country}";
        return "trips";
    }

    @GetMapping("/add")
    public String getAddTripForm(Model model) {
        TripModel tripModel = new TripModel();
        model.addAttribute("trip", tripModel);
        model.addAttribute("hotelModel", new HotelModel());
        model.addAttribute("airportModel", new AirportModel());
        model.addAttribute("foods", FoodOption.values());
        Set<String> continentList = locationService.getListOfContinents();
        model.addAttribute("continents", continentList);
        Set<String> countryList = locationService.getListOfCountires();
        model.addAttribute("countries", countryList);
        List<AirportModel> airportList = airportService.getAirportList();
        model.addAttribute("airports", airportList);
        List<HotelModel> hotelList = hotelService.getHotelsList();
        model.addAttribute("hotels", hotelList);
        Boolean promotion = tripModel.isPromoted();
        model.addAttribute("promotion", promotion);
//        Long duration = tripModel.getDuration();
//        model.addAttribute("duration",duration);
        Double priceAdult = tripModel.getPriceForAdult();
        model.addAttribute("priceAdult", priceAdult);
        Double priceChild = tripModel.getPriceForChild();
        model.addAttribute("priceChild", priceChild);

        return "addTrip";
    }

    @PostMapping("/add")
    public String saveTrip(@ModelAttribute TripModel tripModel, Model model) {
//       model.addAttribute("tripModel", tripModel);
        tripService.PostAddTrip(tripModel);
        return "getTrips";
    }


    @GetMapping("/admin")
    public String getAdminTripList(Model model) {
        List<TripModel> tripList = tripService.getTripList();
        model.addAttribute("trips", tripList);
        return "adminTrips";
    }

    //todo do konsultacji
    @GetMapping("/edit/{id}")
    public String getEditTripForm(@PathVariable(value = "id") Long tripId, Model model) {
        TripModel tripToEdit = tripService.getTripById(tripId);
        String hotelName = tripToEdit.getHotel().getName();
        model.addAttribute("tripToEdit", tripToEdit);
        model.addAttribute("hotelName", hotelName);
        return "adminEditTrip";
    }

    //todo do konsultacji z mentorem
    @PostMapping("/edit/{tripId}")
    public String saveEditTrip(@PathVariable(value = "tripId") Long tripId, Model model, @ModelAttribute TripModel trip) {
        TripModel editedTrip = tripService.getTripById(tripId);
//        editedTrip.setId(tripId);
//        editedTrip.setHotel(trip.getHotel());
//        editedTrip.setDuration(trip.getDuration());
//        editedTrip.setEndDate(trip.getEndDate());
//        editedTrip.setStartDate(trip.getStartDate());
//        editedTrip.setAirportFrom(trip.getAirportFrom());
//        editedTrip.setAirportTo(trip.getAirportTo());
//        editedTrip.setFoodOption(trip.getFoodOption());
//        editedTrip.setNumberOfPlaces(trip.getNumberOfPlaces());
//        editedTrip.setPriceForAdult(trip.getPriceForAdult());
//        editedTrip.setPriceForChild(trip.getPriceForChild());
//        editedTrip.setPromoted(trip.isPromoted());
        tripService.saveEditTrip(editedTrip);
        model.addAttribute("editedTrip", editedTrip);
        return "redirect:/trips/admin";
    }

    //todo dokonczyc implementacje w przypadku gdy wycieczka jest zamowiona lub opatrzyc wyjatkiem.
    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") Long tripId, Model model) {
//        tripOrderService.deleteOrderByTripId(tripId);
        tripService.deleteTrip(tripId);
        return "deleteInfo";
    }
}
