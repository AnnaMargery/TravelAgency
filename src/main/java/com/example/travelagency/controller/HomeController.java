package com.example.travelagency.controller;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.SearchForm;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.HotelService;
import com.example.travelagency.service.LocationService;
import com.example.travelagency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final TripService tripService;
    private final LocationService locationService;
    private final HotelService hotelService;

    @Autowired
    public HomeController(TripService tripService, LocationService locationService, HotelService hotelService) {
        this.tripService = tripService;
        this.locationService = locationService;
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String greeting(Model model){
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("foodOptions", Arrays.asList(FoodOption.values()));
        model.addAttribute("continents", locationService.getListOfContinents());
        model.addAttribute("countries", locationService.getListOfCountries());
        model.addAttribute("standards", hotelService.getListOfHotelStandard());

        model.addAttribute("userRoles", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return "start";
    }



    @PostMapping("/")
    public String searchByFoodOption(@ModelAttribute("searchForm") SearchForm searchForm,
//                                     @ModelAttribute("tripModel") TripModel tripModel,
                                     Model model) {

        try {
            FoodOption foodOption = searchForm.getFoodOption();
            List<TripModel> searchedTrips = tripService.findSelectedTrips(searchForm.getStandard(), searchForm.getFoodOption(), searchForm.getContinent(), searchForm.getCountry());
            model.addAttribute("searchedTrips", searchedTrips);
            model.addAttribute("foodOptions", foodOption);
            model.addAttribute("continents", locationService.getListOfContinents());
            model.addAttribute("countries", locationService.getListOfCountries());
            model.addAttribute("standards", hotelService.getListOfHotelStandard());
            return "getSearchedTrips";
        } catch (ApiRequestException e) {
            return "errorTripSearch";
        }
    }

    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("foodOptions", Arrays.asList(FoodOption.values()));
        model.addAttribute("continents", locationService.getListOfContinents());
        model.addAttribute("countries", locationService.getListOfCountries());
        model.addAttribute("standards", hotelService.getListOfHotelStandard());
        return "searchTrip";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }





    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }



}