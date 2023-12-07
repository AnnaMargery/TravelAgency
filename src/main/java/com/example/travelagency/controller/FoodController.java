package com.example.travelagency.controller;

import com.example.travelagency.model.FoodModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping()
    public ResponseEntity<FoodModel> addHotel(@RequestBody FoodModel foodOptionToAdd) {
        FoodModel food = foodService.addFoodOption(foodOptionToAdd);
        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long foodId) {
        foodService.deleteFoodOption(foodId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FoodModel>> getAllFoodList() {
        List<FoodModel> foods = foodService.getFoodList();
        return ResponseEntity.ok(foods);
    }

    @PutMapping()
    public ResponseEntity<FoodModel> saveEditFood(@RequestBody FoodModel foodToUpdate) {
        FoodModel updatedFood = foodService.saveEditFood(foodToUpdate);
        return ResponseEntity.ok(updatedFood);
    }

}
