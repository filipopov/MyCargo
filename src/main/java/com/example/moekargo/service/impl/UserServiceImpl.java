package com.example.moekargo.service.impl;

import com.example.moekargo.model.OrderStatus;
import com.example.moekargo.model.User;
import com.example.moekargo.repository.UserRepository;
import com.example.moekargo.service.OrderService;
import com.example.moekargo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    private EmailSenderService emailSenderService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String fullName, String email) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || fullName==null || fullName.isEmpty())
            throw new IllegalArgumentException();
        if(!password.equals(repeatPassword))
            throw new IllegalArgumentException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new IllegalArgumentException();

        User user = new User(username, passwordEncoder.encode(password), fullName, email);
//        this.emailSenderService.sendEmail(email, "Активиран нов кориснички профил", "Добредојдовте во HealthZilla Essentials! Вашиот нов профил е активиран. Можете да продолжите!");
        return this.userRepository.save(user);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
