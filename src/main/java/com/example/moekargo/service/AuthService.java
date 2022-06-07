package com.example.moekargo.service;

import com.example.moekargo.model.User;

public interface AuthService {
    User save(User user);
    User login(String username, String password);
}
