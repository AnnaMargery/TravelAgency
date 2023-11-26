package com.example.travelagency.service;

import com.example.travelagency.model.UserModel;
import com.example.travelagency.model.UserRole;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initRoles() {

        UserRole adminRole = new UserRole();
        adminRole.setName("ADMIN");

        UserRole adminSaved = userRoleRepository.save(adminRole);

        UserModel admin = UserModel.builder()
                .password(passwordEncoder.encode("admin"))
                .username("admin")
                .email("admin@admin.com")
                .userRoles(Set.of(adminSaved))
                .build();
        userRepository.save(admin);

        UserRole userRole = new UserRole();
        userRole.setName("USER");

        UserRole userSaved = userRoleRepository.save(userRole);

        UserModel user = UserModel.builder()
                .password(passwordEncoder.encode("user"))
                .username("user")
                .email("user@user.com")
                .userRoles(Set.of(userSaved))
                .build();
        userRepository.save(user);

    }



}