package com.example.moekargo.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Entity
@Table(name = "cargo_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sender sender;

    @ManyToOne
    private Receiver receiver;

    private double price;

    private String comment;

    @Enumerated(value = EnumType.STRING)
    private Pays pays;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime localDateTime;

    @ManyToOne
    private User user;

    public Order() {}

    public Order(Sender sender, Receiver receiver, double price, Pays pays, String comment, LocalDateTime localDateTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.price = price;
        this.pays = pays;
        this.comment = comment;
        this.localDateTime = localDateTime;
        this.orderStatus = OrderStatus.NEW;
        this.user = null;
    }

    public Order(Sender sender, Receiver receiver, double price, Pays pays, String comment, LocalDateTime now, User user) {
        this.sender = sender;
        this.receiver = receiver;
        this.price = price;
        this.pays = pays;
        this.comment = comment;
        this.localDateTime = now;
        this.orderStatus = OrderStatus.NEW;
        this.user = user;
    }
}
