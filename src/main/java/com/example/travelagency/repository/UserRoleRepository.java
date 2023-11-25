package com.example.travelagency.repository;

import com.example.travelagency.model.UserModel;
import com.example.travelagency.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
