package com.example.travelagency.repository;

import com.example.travelagency.model.AddressModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Return address list by Continent")
    void whenContinentGiven_thenAddressListIsReturned() {
        //given
        String continent = "Europe";
        //when
        List<AddressModel> actual = addressRepository.findAllByLocationContinentContains(continent);
        //then
        assertThat(actual).hasSize(16);
    }
}