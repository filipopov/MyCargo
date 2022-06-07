package com.example.moekargo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_sender")
public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String city;

    private String streetName;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Sender() {
    }

    public Sender(String fullName, String phoneNumber, String city, String streetName) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.streetName = streetName;
        this.orders = new ArrayList<>();
    }
}
