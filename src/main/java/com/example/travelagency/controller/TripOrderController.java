package com.example.travelagency.controller;


import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.service.TripOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class TripOrderController {
    private final TripOrderService tripOrderService;

    @GetMapping()
    public String getTripList(Model model){
        List<TripOrderModel> tripOrders = tripOrderService.getOrderList();
        model.addAttribute("orders",tripOrders);
        return "orders";
    }

}
