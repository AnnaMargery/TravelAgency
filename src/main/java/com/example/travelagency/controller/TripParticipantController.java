package com.example.travelagency.controller;

import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.model.TripParticipantModel;
import com.example.travelagency.service.TripOrderService;
import com.example.travelagency.service.TripParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TripParticipantController {
    private final TripParticipantService tripParticipantService;
    private final TripOrderService tripOrderService;

    @GetMapping("/addParticipant/{id}")
    public String getAddParticipantForm(@PathVariable("id") Long id, Model model) {
        TripParticipantModel participant = new TripParticipantModel();
        model.addAttribute("participant", participant);

        model.addAttribute("orderId", id);

       return "addParticipant";
    }

    @PostMapping("/addParticipant/{id}")
    public String saveParticipant(@ModelAttribute TripParticipantModel participant, @PathVariable("id") Long id) {
        participant.setTripOrder(tripOrderService.getOrderById(id));
        tripParticipantService.postAddParticipant(participant);

        return "redirect:/orders/details/"+id;
    }
}
