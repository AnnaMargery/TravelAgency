package com.example.travelagency.service;

import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.repository.TripOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripOrderService {
    private final TripOrderRepository tripOrderRepository;


    public List<TripOrderModel> getOrderList(){
        return tripOrderRepository.findAll();
    }
}
