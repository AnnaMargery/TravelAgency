package com.example.travelagency.service;

import com.example.travelagency.exception.ApiInputException;
import com.example.travelagency.exception.ApiRequestException;
import com.example.travelagency.model.FoodModel;
import com.example.travelagency.model.HotelModel;
import com.example.travelagency.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodModel addFoodOption(FoodModel foodToAdd) {
        if (!foodRepository.existsById(foodToAdd.getId())) {
            return foodRepository.save(foodToAdd);
        }
        throw new ApiInputException("Food option exists for id: " + foodToAdd.getId());
    }

    public void deleteFoodOption(Long foodId) {
        if (foodRepository.existsById(foodId)) {
            foodRepository.deleteById(foodId);
        }
        throw new ApiInputException("Food option not found for id: " + foodId);
    }

    public List<FoodModel> getFoodList() {
        if (!foodRepository.findAll().isEmpty()) {
            return foodRepository.findAll();
        }
        throw new ApiRequestException("Food list is empty");
    }

    public List<String> getFoodOptions() {
        List<String> foods = new ArrayList<>();
        foods.add("Only Bed");
        foods.add("Bed and Breakfast");
        foods.add("Half-Board");
        foods.add("Full-Board");
        foods.add("All-Inclusive");
        return foods;
    }

    public FoodModel saveEditFood(FoodModel foodToEdit) {
        if (foodRepository.existsById(foodToEdit.getId())) {
            return foodRepository.save(foodToEdit);
        }
        throw new ApiInputException("Food not found for id: " + foodToEdit.getId());
    }

}
