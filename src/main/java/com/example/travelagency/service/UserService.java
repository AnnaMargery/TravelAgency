package com.example.travelagency.service;

import com.example.travelagency.model.UserModel;
import com.example.travelagency.model.UserRole;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserRoleRepository userRoleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initRoles() {

        UserRole adminRole = new UserRole();
        adminRole.setName("ADMIN");

        UserRole adminSaved = userRoleRepo.save(adminRole);

        UserModel admin = UserModel.builder()
                .password(passwordEncoder.encode("admin"))
                .username("admin")
                .email("admin@admin.com")
                .userRoles(Set.of(adminSaved))
                .build();
        userRepo.save(admin);

        UserRole userRole = new UserRole();
        userRole.setName("USER");

        UserRole userSaved = userRoleRepo.save(userRole);

        UserModel user = UserModel.builder()
                .password(passwordEncoder.encode("user"))
                .username("user")
                .email("user@user.com")
                .userRoles(Set.of(userSaved))
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