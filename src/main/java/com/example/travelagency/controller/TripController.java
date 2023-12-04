package com.example.travelagency.controller;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/trips")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping()
    public String getTripList(Model model){
        List<TripModel>tripList = tripService.getTripList();
        model.addAttribute("trips",tripList);
        return "trips";
    }






}
