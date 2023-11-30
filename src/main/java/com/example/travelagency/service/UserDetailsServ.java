package com.example.travelagency.service;

import com.example.travelagency.model.UserModel;
import com.example.travelagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServ implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsernameOrEmail(username, username).get();
        if (userModel == null) {
            new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authorities = userModel.getUserRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new User(username, userModel.getPassword(), authorities);
    }
}
