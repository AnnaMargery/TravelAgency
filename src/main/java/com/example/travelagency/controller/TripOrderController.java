package com.example.travelagency.controller;

import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.TripModel;
import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.model.TripParticipantModel;
import com.example.travelagency.service.TripOrderService;
import com.example.travelagency.service.TripParticipantService;
import com.example.travelagency.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class TripOrderController {
    private final TripOrderService tripOrderService;
    private final TripParticipantService tripParticipantService;
    private final TripService tripService;

    @GetMapping
    public String getOrdersList(Model model) {
        List<TripOrderModel> orderList = tripOrderService.getOrderList();
        model.addAttribute("orders", orderList);
        return "orders";
    }


    @GetMapping("/add/{id}")
    public String getAddTripOrderFormById(@PathVariable("id") Long id, Model model) {
        TripOrderModel tripOrderModel = new TripOrderModel();
        model.addAttribute("order", tripOrderModel);
        model.addAttribute("tripId", id);
        return "addOrder";
    }


    @PostMapping("/add/{id}")
    public String saveOrder(@PathVariable("id") Long id, TripOrderModel tripOrderModel) {
        TripModel tripById = tripService.getTripById(id);
        tripOrderModel.setTrip(tripById);
        if (tripById.getNumberOfPlaces() >= (tripOrderModel.getNumberOfChildren() + tripOrderModel.getNumberOfAdults())) {
            Integer updatedPNumberOfPlaces = tripById.getNumberOfPlaces() - (tripOrderModel.getNumberOfChildren() + tripOrderModel.getNumberOfAdults());
            tripById.setNumberOfPlaces(updatedPNumberOfPlaces);
            tripOrderService.PostAddTripOrder(tripOrderModel);
        } else {
            throw new ApiRequestException("Not enough places in the trip");
        }
        return "redirect:/orders/details/" + tripOrderModel.getId();
    }


    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") Long id, Model model) {
        TripOrderModel orderById = tripOrderService.getOrderById(id);
        model.addAttribute("details", orderById);
        model.addAttribute("orderId", id);
        List<TripParticipantModel> participantsByTripOrderId = tripParticipantService.findParticipantsByTripOrderId(id);
        model.addAttribute("participantsList", participantsByTripOrderId);
        return "orderDetails";
    }
}