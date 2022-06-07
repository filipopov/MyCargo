package com.example.moekargo.service;

import com.example.moekargo.model.Order;
import com.example.moekargo.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    List<Order> findAll();
    List<Order> findAllNew(User user);
    List<Order> findAllActive(User user);
    List<Order> findAllDelivered(User user);
    List<Order> findAllReturned(User user);
    List<Order> findAllCanceled(User user);
    List<Integer> numberOfItems(User user);
    Optional<Order> findById(Long id);
    List<Double> getMoney(User user);
}
