package com.example.travelagency.controller;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.AirportModel;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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
        List<AirportModel> airportList = airportService.getAirportList();
        model.addAttribute("airports", airportList);
        List<HotelModel> hotelList = hotelService.getHotelsList();
        model.addAttribute("hotels", hotelList);
        Boolean promotion = tripModel.isPromoted();
        model.addAttribute("promotion", promotion);
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
//    @GetMapping("/edit/{id}")
//    public String getEditTripForm(@PathVariable(value = "id") Long tripId, Model model,
//                                  Model hotelModel, Model airportModel) {
//        final TripModel tripE = new TripModel();
//
//        model.addAttribute("tripE", tripE);
//        TripModel tripToEdit = tripService.getTripById(tripId);
//        model.addAttribute("tripToEdit", tripToEdit);
//        model.addAttribute("hotelModel", tripToEdit.getHotel());
//        List<HotelModel> hotelList = hotelService.getHotelsList();
//        model.addAttribute("hotels", hotelList);
//        model.addAttribute("airportModel", tripToEdit.getAirportTo());
//        List<AirportModel> airportList = airportService.getAirportList();
//        model.addAttribute("airports", airportList);
//        model.addAttribute("foods", FoodOption.values());
//        model.addAttribute("numberOfPlaces", tripToEdit.getNumberOfPlaces());
//        model.addAttribute("promotion", tripToEdit.isPromoted());
//
//        model.addAttribute("priceAdult", tripToEdit.getPriceForAdult());
//
//        model.addAttribute("priceChild", tripToEdit.getPriceForChild());
//        return "adminEditTrip";
//    }

    @GetMapping("/edit/{id}")
    public String getEditTripForm(@PathVariable(value = "id") Long tripId, Model model,
                                Model hotelModel, Model airportModel) {
        model.addAttribute("tripToEdit", tripService.getTripById(tripId));
        model.addAttribute("hotelModel", tripService.getTripById(tripId).getHotel());
        model.addAttribute("airportModel", tripService.getTripById(tripId).getAirportTo());
        return "/adminEditTrip";
    }
    @PostMapping("/edit/{tripId}")
    public String saveEditTrip(@ModelAttribute("tripToEdit") TripModel tripModel, Model model){
        this.tripService.saveEditTrip(tripModel);
        return "/adminTrips";
    }


//    @PostMapping("/edit/{id}")
//    public RedirectView saveEditTrip(@PathVariable(value = "id") Long id, Model model, TripModel editTrip) { {
////        TripModel tripToEdit = tripService.getTripById(id);
////        model.addAttribute("tripToEdit",tripToEdit);
//        List<AirportModel> airportList = airportService.getAirportList();
//        List<HotelModel> hotelList = hotelService.getHotelsList();
//        model.addAttribute("hotels", hotelList);
//        model.addAttribute("airports", airportList);
//
//        tripService.saveEditTrip(editTrip);
//        return new RedirectView("/trips/admin");
//    }


    //todo do konsultacji z mentorem
//    @PutMapping("/edit/{tripId}")
//    public String saveEditTrip1(@PathVariable(value = "tripId") Long tripId, Model model, @ModelAttribute TripModel trip) {
//        TripModel editedTrip = tripService.getTripById(tripId);
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
//        tripService.saveEditTrip(editedTrip);
//        model.addAttribute("editedTrip", editedTrip);
//        return "redirect:/trips/admin";
//    }


//    //todo do konsultacji z mentorem
//    @PutMapping("/edit/{id}")
//    public String saveEditTrip1(@PathVariable(value = "id") Long tripId, Model model, @ModelAttribute("tripToEdit") TripModel tripModel) {
//        TripModel editedTrip = tripService.getTripById(tripId);
//        TripModel updatedTrip = tripService.saveEditTrip(editedTrip);
//       model.addAttribute("tripToEdit", updatedTrip);
//        return "redirect:/trips/admin";
//    }

    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") Long tripId) {
        if (!tripOrderService.getOrdersByTripId(tripId).isEmpty()) {
            throw new ApiRequestException("Trip can't be removed - trip orders exists");
        }
        tripService.deleteTrip(tripId);
        return "deleteInfo";
    }

    @GetMapping("/promoted")
    public String getPromotedTrips(Model model) {
        List<TripModel> promotedTrips = tripService.getPromotedTrips();
        model.addAttribute("promotedTrips", promotedTrips);
        return "redirect:/trips/promoted";
    }
}

