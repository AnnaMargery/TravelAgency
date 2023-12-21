package com.example.travelagency.repository;

import com.example.travelagency.model.FoodOption;
import com.example.travelagency.model.TripModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class TripRepositoryTest {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    TestEntityManager testEntityManager;


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
        //when
        List<TripModel> allTrips = tripRepository.findAll();
        //then
        assertThat(allTrips).hasSize(14);
    }

    @Test
    void returnTripsByContinent(){
        //when
        List<TripModel> tripsInEurope = tripRepository.findTripModelByHotelAddressLocationContinent("Europe");
        //then
        assertThat(tripsInEurope).hasSize(9);
    }

    @Test
    void findTripByCountry(){
        //when
        List<TripModel> tripsInChina = tripRepository.findTripModelByHotelAddressLocationCountry("China");
        //then
        assertThat(tripsInChina).hasSize(2);
    }

    @Test
    void returnTripsByFoodOption(){
        //given
        FoodOption foodOption = FoodOption.BB;
        //when
        List<TripModel> tripsWithBB = tripRepository.findTripModelByFoodOption(foodOption);
        //then
        assertThat(tripsWithBB).hasSize(2);
    }

    @Test
    void returnPromotedTrips(){
        //when
        List<TripModel> promotedTrips = tripRepository.findByPromotedIsTrue();
        //then
        assertThat(promotedTrips).hasSize(12);
    }

    @Test
    void returnTripsByOptions(){
        //given
        Integer hotelStandard = 5;
        FoodOption foodOption = FoodOption.BB;
        String country = "France";
        String continent = "Europe";
        //when
        List<TripModel> selectedTrips = tripRepository.findSelectedTrip(hotelStandard, foodOption, continent, country);
        //then
        assertThat(selectedTrips).hasSize(1);
    }

}