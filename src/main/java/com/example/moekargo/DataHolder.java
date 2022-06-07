package com.example.moekargo;

import com.example.moekargo.model.*;
import com.example.moekargo.service.OrderService;
import com.example.moekargo.service.ReceiverService;
import com.example.moekargo.service.SenderService;
import com.example.moekargo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class DataHolder {

    private final ReceiverService receiverService;
    private final SenderService senderService;
    private final OrderService orderService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){

        Receiver receiver1 = new Receiver("Trajko Trajkovski", "0123456789", "Skopje", "Bul ASNOM");
        Receiver receiver2 = new Receiver("Petko Petkovski", "9876543210", "Skopje", "Hristo Smirnenski");

        this.receiverService.save(receiver1);
        this.receiverService.save(receiver2);

        Sender sender = new Sender("Filip Popov", "078278381", "Bitola", "Mirche Acev");

        this.senderService.save(sender);

        Order order1 = new Order(sender, receiver1, 1000, Pays.RECEIVER, "comment1", LocalDateTime.now());
        Order order2 = new Order(sender, receiver2, 2000, Pays.RECEIVER, "comment2", LocalDateTime.now());

        receiver1.getOrders().add(order1);
        receiver2.getOrders().add(order2);

        this.receiverService.save(receiver1);
        this.receiverService.save(receiver2);

        sender.getOrders().add(order1);
        sender.getOrders().add(order2);

        this.senderService.save(sender);

        this.orderService.save(order1);
        this.orderService.save(order2);

        User user_sender = new User("filipopov", this.passwordEncoder.encode("fp"), "Filip Popov", "someemail@email", Role.SENDER);
        user_sender.getOrderList().add(order1);
        user_sender.getOrderList().add(order2);
        this.userService.save(user_sender);

        User userTemp = new User("temp", this.passwordEncoder.encode("t"), "Filip Popov", "asdasd", Role.SENDER);
        this.userService.save(userTemp);

        User user_supplier = new User("dostavuvac", this.passwordEncoder.encode("d"), "Dostavuvac", "someemail@email", Role.SUPPLIER);
        this.userService.save(user_supplier);

        order1.setUser(user_sender);
        order2.setUser(user_sender);

        this.orderService.save(order1);
        this.orderService.save(order2);

    }
}
