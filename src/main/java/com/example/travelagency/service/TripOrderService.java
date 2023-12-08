package com.example.travelagency.service;

import com.example.travelagency.model.TripModel;
import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.repository.TripOrderRepository;
import com.example.travelagency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripOrderService {
    private final TripOrderRepository tripOrderRepository;
    private final TripRepository tripRepository;


    public List<TripOrderModel> getOrderList() {
        return tripOrderRepository.findAll();
    }

    public Double totalPrice(long tripId, int numberOfAdults, int numberOfChildren) {
        Optional<TripModel> tripById = tripRepository.findById(tripId);
        Double priceForAdult = tripById.get().getPriceForAdult();
        Double priceForChildren = tripById.get().getPriceForChild();

        Double totalPrice = numberOfAdults * priceForAdult + numberOfChildren * priceForChildren;
        return totalPrice;
    }

    public void PostAddTripOrder(TripOrderModel tripOrder) {
        tripOrderRepository.save(tripOrder);
    }
}
