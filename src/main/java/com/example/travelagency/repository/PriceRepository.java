package com.example.travelagency.repository;

import com.example.travelagency.model.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel,Long> {
    PriceModel findByFood_FoodOption(String foodOption);
}
