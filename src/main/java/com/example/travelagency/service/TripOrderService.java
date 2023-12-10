package com.example.travelagency.service;

import com.example.travelagency.model.TripOrderModel;
import com.example.travelagency.repository.TripOrderRepository;
import com.example.travelagency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Double priceForAdult = tripOrder.getTrip().getPriceForAdult();
        Double priceForChild = tripOrder.getTrip().getPriceForChild();
        Double totalPrice = tripOrder.getNumberOfAdults() * priceForAdult + tripOrder.getNumberOfChildren() * priceForChild;
        tripOrder.setTotalPrice(totalPrice);
        tripOrderRepository.save(tripOrder);
    }

    public TripOrderModel getOrderById(Long id) {
        return tripOrderRepository.getTripOrderModelById(id);
    }

    public List<TripOrderModel> getOrdersByTripId(Long tripId){
        return tripOrderRepository.getAllByTripId(tripId);
    }
}
