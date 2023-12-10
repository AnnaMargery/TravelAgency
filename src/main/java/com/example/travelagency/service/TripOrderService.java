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

    //todo do sprawdzenia- napisana poniewaz nie da sie usuwac wycieczki na ktore sa zamowienia-
    // wiec w metodzie usuwania tripa trzeba uwzglednic usuwanie ordera- ale jeszcze trzeba usunac powiazania z participantami
//    public void deleteOrderByTripId(Long tripId) {
//        List<TripOrderModel> orders = tripOrderRepository.findAll();
//        for (TripOrderModel order : orders) {
//            if (order.getTrip().getId() == tripId) {
//                tripOrderRepository.deleteById(order.getId());
//            }
//        }
//    }
}
