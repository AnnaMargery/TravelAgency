package com.example.travelagency.service;

import com.example.travelagency.model.UserModel;
import com.example.travelagency.model.UserRole;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserRoleRepository userRoleRepo;



    @PostConstruct
    public void init() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserRole userRole = new UserRole();
        userRole.setName("ADMIN");

        UserRole saved = userRoleRepo.save(userRole);

        UserModel user = UserModel.builder()
                .password(passwordEncoder.encode("test"))
                .username("test")
                .email("test@test.com")
                .userRoles(Set.of(saved))
                .build();

        userRepo.save(user);

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepo.findByUsernameOrEmail(username, username).get();
        if(userModel ==null){
            new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authorities = userModel.getUserRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, userModel.getPassword(),authorities);
    }
}