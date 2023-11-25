package com.example.travelagency.repository;

import com.example.travelagency.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsernameOrEmail(String username, String email);
}
