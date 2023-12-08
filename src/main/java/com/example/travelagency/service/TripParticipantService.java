package com.example.travelagency.service;

import com.example.travelagency.model.TripParticipantModel;
import com.example.travelagency.repository.TripParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripParticipantService {
    private final TripParticipantRepository tripParticipantRepository;

    public void postAddParticipant(TripParticipantModel participant){
        tripParticipantRepository.save(participant);
    }

    public List<TripParticipantModel> findParticipantsByTripOrderId (Long tripOrderId){
        return tripParticipantRepository.findAllByTripOrderId(tripOrderId);
    }

}
