package com.example.travelagency.controller;


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

    @GetMapping("/add/{id}")
    public String getAddTripFormById(@PathVariable("id") Long id, Model model) {
        TripOrderModel tripOrderModel = new TripOrderModel();
        tripOrderModel.setId(id);
        model.addAttribute("order", tripOrderModel);
        Integer numberOfAdults = tripOrderModel.getNumberOfAdults();
        model.addAttribute("numberOfAdults", numberOfAdults);
        Integer numberOfChildren = tripOrderModel.getNumberOfChildren();
        model.addAttribute("numberOfChildren", numberOfChildren);
        return "addOrder";
    }


    @PostMapping("/add/{id}")
    public String saveOrder(@PathVariable("id") Long id, TripOrderModel tripOrderModel) {
        tripOrderModel.setTrip(tripService.getTripById(id));
        tripOrderService.PostAddTripOrder(tripOrderModel);
        return "redirect:/orders/details/"+id;
    }


    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") Long id, Model model) {
        TripOrderModel orderById = tripOrderService.getOrderById(id);
        model.addAttribute("details", orderById);
        List<TripParticipantModel> participantsByTripOrderId = tripParticipantService.findParticipantsByTripOrderId(id);
        model.addAttribute("participantsList", participantsByTripOrderId);

        return "orderDetails";
    }
}