package com.example.travelagency.controller;

import com.example.travelagency.model.TripParticipantModel;
import com.example.travelagency.service.TripOrderService;
import com.example.travelagency.service.TripParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class TripParticipantController {
    private final TripParticipantService tripParticipantService;
    private final TripOrderService tripOrderService;

    @GetMapping("/addParticipant/{id}")
    public String getAddParticipantForm(@PathVariable("id") Long id, Model model) {
        TripParticipantModel participant = new TripParticipantModel();
        model.addAttribute("participant", participant);
        participant.setTripOrder(tripOrderService.getOrderById(id));

        String firstName = participant.getFirstName();
        model.addAttribute("firstName", firstName);
        String lastName = participant.getLastName();
        model.addAttribute("lastName", lastName);
        LocalDate birthDate = participant.getBirthDate();
        model.addAttribute("birthDate", birthDate);

        return "addParticipant";
    }

    @PostMapping("/addParticipant")
    public String saveParticipant(@ModelAttribute TripParticipantModel participant) {
        tripParticipantService.postAddParticipant(participant);

        return "orders";
    }
}
