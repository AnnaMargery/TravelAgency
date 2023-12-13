package com.example.travelagency.repository;

import com.example.travelagency.model.TripModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
class TripRepositoryTest {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    TestEntityManager testEntityManager;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        TripModel trip = new TripModel();
//        trip.setId(111l);
//        trip.setStartDate(new Date(2024, 06, 03));
//        trip.setEndDate(new Date(2024, 06, 04));
//        trip.setNumberOfPlaces(20);
//        trip.setPriceForAdult(100d);
//        trip.setPriceForChild(50d);
//        tripRepository.save(trip);
//    }

    @Test
    void givenTripObject_whenSave_thenReturnSavedTrip() {
        //given
        TripModel tripModel = new TripModel();
        tripModel.setDuration(1l);
        tripModel.setNumberOfPlaces(30);
        tripModel.setPriceForAdult(150d);
        tripModel.setPriceForChild(50d);
        //when
        TripModel save = tripRepository.save(tripModel);

        //then
        assertThat(testEntityManager.find(TripModel.class, save.getId())).isEqualTo(tripModel);
        assertThat(tripModel).isNotNull();
        assertThat(tripModel.getPriceForAdult()).isEqualTo(150d);

    }

    @Test
    void givenTripCreated_whenFindById_thenSuccess() {
        //given
        TripModel trip = new TripModel();
        trip.setDuration(1l);
        trip.setNumberOfPlaces(30);
        trip.setPriceForAdult(150d);
        trip.setPriceForChild(50d);
        testEntityManager.persist(trip);
        //when
        Optional<TripModel> tripById = tripRepository.findById(trip.getId());
        //then
        assertThat(tripById).contains(trip);

    }

    @Test
    void returnAllTrips() {
        //given
        TripModel tripModel = new TripModel();
        tripModel.setId(110l);

        tripModel.setDuration(1l);
        tripModel.setNumberOfPlaces(30);
        tripModel.setPriceForAdult(150d);
        tripModel.setPriceForChild(50d);
        tripRepository.save(tripModel);
        //when
        List<TripModel> allTrips = tripRepository.findAll();
        //then
        assertThat(allTrips).hasSize(2);
    }
}