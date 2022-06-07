package com.example.moekargo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_receiver")
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String city;

    private String streetName;

    private String comment;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Receiver() {
    }

    public Receiver(String fullName, String phoneNumber, String city, String streetName) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.streetName = streetName;
        this.orders = new ArrayList<>();
    }
}
