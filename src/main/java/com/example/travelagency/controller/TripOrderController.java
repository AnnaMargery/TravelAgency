package com.example.travelagency.controller;


import com.example.travelagency.model.TripModel;
import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.service.TripOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class TripOrderController {
    private final TripOrderService tripOrderService;


    @GetMapping("/addOrder")
    public String getAddTripForm(Model model) {
        TripOrderModel tripOrderModel = new TripOrderModel();
        model.addAttribute("order", tripOrderModel);
        Integer numberOfAdults = tripOrderModel.getNumberOfAdults();
        model.addAttribute("numberOfAdults", numberOfAdults);
        Integer numberOfChildren = tripOrderModel.getNumberOfChildren();
        model.addAttribute("numberOfChildren", numberOfChildren);

        //Double totalPrice = tripOrderService.totalPrice(tripOrderModel.getTrip().getId(), numberOfAdults, numberOfChildren);
        //model.addAttribute("totalPrice", totalPrice);
        return "addOrder";
    }

    @PostMapping("/addOrder")
    public String saveOrder(@ModelAttribute TripOrderModel tripOrderModel) {

        tripOrderService.PostAddTripOrder(tripOrderModel);
        return "orderAdded";
    }
}