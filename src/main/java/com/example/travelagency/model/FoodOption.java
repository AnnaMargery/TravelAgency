package com.example.travelagency.model;

public enum FoodOption {
    OB("Only Bed"),
    BB("Bed and Breakfast"),
    HB("Half-Board"),
    FB("Full-Board"),
    ALLINC("All-Inclusive");

    private final String foodOption;

    FoodOption(String foodOption) {
        this.foodOption = foodOption;
    }

    public String getFoodOption() {
        return foodOption;
    }
}
