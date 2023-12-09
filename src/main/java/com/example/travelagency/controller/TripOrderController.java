package com.example.travelagency.controller;


import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.model.TripParticipantModel;
import com.example.travelagency.service.TripOrderService;
import com.example.travelagency.service.TripParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class TripOrderController {
    private final TripOrderService tripOrderService;
    private final TripParticipantService tripParticipantService;

    @GetMapping
    public String getOrdersList(Model model) {
        List<TripOrderModel> orderList = tripOrderService.getOrderList();
        model.addAttribute("orders", orderList);
        return "orders";
    }

    @GetMapping("/addOrder")
    public String getAddTripForm(Model model) {
        TripOrderModel tripOrderModel = new TripOrderModel();
        model.addAttribute("order", tripOrderModel);
        Integer numberOfAdults = tripOrderModel.getNumberOfAdults();
        model.addAttribute("numberOfAdults", numberOfAdults);
        Integer numberOfChildren = tripOrderModel.getNumberOfChildren();
        model.addAttribute("numberOfChildren", numberOfChildren);


//        List<TripParticipantModel> participantsList = tripParticipantService.findParticipantsByTripOrderId(tripOrderModel.getId());
//        model.addAttribute("participantsList", participantsList);

        //jak przekazać TripId?
        //Double totalPrice = tripOrderService.totalPrice(tripOrderModel.getTrip().getId(), numberOfAdults, numberOfChildren);
        //model.addAttribute("totalPrice", totalPrice);
        return "addOrder";
    }

    @PostMapping("/addOrder")
    public String saveOrder(@ModelAttribute TripOrderModel tripOrderModel) {

        tripOrderService.PostAddTripOrder(tripOrderModel);
        return "orderDetails";
    }

    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") Long id, Model model){
        TripOrderModel orderById = tripOrderService.getOrderById(id);
        model.addAttribute("details", orderById);
        List<TripParticipantModel> participantsByTripOrderId = tripParticipantService.findParticipantsByTripOrderId(id);
        model.addAttribute("participantsList", participantsByTripOrderId);

        return "orderDetails";
    }
}