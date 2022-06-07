package com.example.moekargo.service.impl;

import com.example.moekargo.model.Order;
import com.example.moekargo.model.OrderStatus;
import com.example.moekargo.model.User;
import com.example.moekargo.repository.OrderRepository;
import com.example.moekargo.service.OrderService;
import com.example.moekargo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllNew(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        return orders.stream().filter(i->i.getOrderStatus().equals(OrderStatus.NEW)).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllActive(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        return orders.stream().filter(i->i.getOrderStatus().equals(OrderStatus.ACTIVE)).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllDelivered(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        return orders.stream().filter(i->i.getOrderStatus().equals(OrderStatus.DELIVERED)).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllReturned(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        return orders.stream().filter(i->i.getOrderStatus().equals(OrderStatus.RETURNED)).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllCanceled(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        return orders.stream().filter(i->i.getOrderStatus().equals(OrderStatus.CANCELED)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> numberOfItems(User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        List<Integer> result = new ArrayList<>();
        result.add(orders.stream().filter(i -> i.getOrderStatus().equals(OrderStatus.NEW)).toList().size());
        result.add(orders.stream().filter(i -> i.getOrderStatus().equals(OrderStatus.ACTIVE)).toList().size());
        result.add(orders.stream().filter(i -> i.getOrderStatus().equals(OrderStatus.DELIVERED)).toList().size());
        result.add(orders.stream().filter(i -> i.getOrderStatus().equals(OrderStatus.RETURNED)).toList().size());
        result.add(orders.stream().filter(i -> i.getOrderStatus().equals(OrderStatus.CANCELED)).toList().size());
        return result;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public List<Double> getMoney(User user) {
        List<Double> result = new ArrayList<>();
        result.add(this.findAllNew(user).stream().map(Order::getPrice).reduce((double) 0, Double::sum) + this.findAllActive(user).stream().map(Order::getPrice).reduce((double) 0, Double::sum));
        result.add(this.findAllDelivered(user).stream().map(Order::getPrice).reduce((double) 0, Double::sum));

        return result;
    }
}
