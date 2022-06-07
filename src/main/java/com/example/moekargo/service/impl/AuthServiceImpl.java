package com.example.moekargo.service.impl;

import com.example.moekargo.model.User;
import com.example.moekargo.repository.UserRepository;
import com.example.moekargo.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new IllegalArgumentException();
        Optional<User> user = this.userRepository.findByUsernameAndPassword(username, password);
        if(user.isPresent()){
            if(user.get().getIsActivated())
                return user.get();
            else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
//        return this.userRepository.findByUsernameAndPassword(username, password)
//                .orElseThrow(IllegalArgumentException::new);
    }

}
