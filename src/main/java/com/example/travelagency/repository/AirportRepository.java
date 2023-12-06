package com.example.travelagency.repository;

import com.example.travelagency.model.AirportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
@Repository
public interface AirportRepository extends JpaRepository<AirportModel,Long> {
}



