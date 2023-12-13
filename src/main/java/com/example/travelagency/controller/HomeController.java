package com.example.travelagency.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("userRoles", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return "start";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/start")
    public String start(Model model){
        return "start";
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