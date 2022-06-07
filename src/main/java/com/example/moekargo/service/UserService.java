package com.example.moekargo.service;

import com.example.moekargo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String fullName, String email);
    Optional<User> findByUsername(String username);
    User save(User user);
    List<User> findAll();
}
