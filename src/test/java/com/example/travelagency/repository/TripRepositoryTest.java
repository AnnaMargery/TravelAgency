package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
class TripRepositoryTest {
    @Mock
    private TripRepository tripRepository;

    private TripModel tripModel;

        @BeforeEach
    void setUp() {
        TripModel trip = new TripModel();
        trip.setId(111l);
        trip.setStartDate(new Date(2024, 06, 03));
        trip.setEndDate(new Date(2024, 06, 04));
        trip.setNumberOfPlaces(20);
        trip.setPriceForAdult(100d);
        trip.setPriceForChild(50d);
        tripRepository.save(trip);
    }

    @Test
    void givenTripObject_whenSave_thenReturnSavedTrip() {
        //given
        tripModel = new TripModel();
        tripModel.setId(110l);
        tripModel.setStartDate(new Date(2024, 06, 03));
        tripModel.setEndDate(new Date(2024, 06, 04));
        tripModel.setDuration(1l);
        tripModel.setNumberOfPlaces(30);
        tripModel.setPriceForAdult(150d);
        tripModel.setPriceForChild(50d);
        //when
        tripRepository.save(tripModel);

        //then
        assertThat(tripModel).isNotNull();
        assertThat(tripModel.getPriceForAdult()).isEqualTo(150d);

    }
//    @Test
//    void returnAllTrips() {
//        //given
//        tripModel = new TripModel();
//        tripModel.setId(110l);
//        tripModel.setStartDate(new Date(2024, 06, 03));
//        tripModel.setEndDate(new Date(2024, 06, 04));
//        tripModel.setDuration(1l);
//        tripModel.setNumberOfPlaces(30);
//        tripModel.setPriceForAdult(150d);
//        tripModel.setPriceForChild(50d);
//        tripRepository.save(tripModel);
//        //when
//        List<TripModel> allTrips = tripRepository.findAll();
//        //then
//        assertThat(allTrips).hasSize(2);
//    }
}