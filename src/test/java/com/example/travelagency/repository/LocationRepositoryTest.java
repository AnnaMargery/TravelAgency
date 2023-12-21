package com.example.travelagency.repository;

import com.example.travelagency.model.LocationModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach()

    @Test
    @DisplayName("Location list by given Continent")
    void whenContinentIsGiven_ThenReturnLocationList() {
        //given
        String continent = "Europe";
        //when
        List<LocationModel> actual = locationRepository.findAllLocationsByContinent(continent);
        //then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(13);
    }

    @Test
    @DisplayName("Location list by given Country")
    void findAllLocationsByCountry() {
        //given
        String country = "France";
        //when
        List<LocationModel> actual = locationRepository.findAllLocationsByCountry(country);
        //then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(1);
    }

    @Test
    @DisplayName("Location by given city")
    void findLocationByCity() {
        //given
        String city = "Cracow";
        //when
        LocationModel actual = locationRepository.findLocationByCity(city);
        //then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getCity()).isEqualTo("Cracow");
    }

}