package com.example.moekargo.controller;

import com.example.moekargo.model.*;
import com.example.moekargo.service.OrderService;
import com.example.moekargo.service.ReceiverService;
import com.example.moekargo.service.SenderService;
import com.example.moekargo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SenderService senderService;
    private final ReceiverService receiverService;
    private final UserService userService;

    @GetMapping("/home")
    public String getHome(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAll());
        model.addAttribute("sizes", this.orderService.numberOfItems(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("username", request.getRemoteUser());
        return "home";
    }

    @GetMapping("/addNewOrders")
    public String getOrderForm(Model model, HttpServletRequest request){

        model.addAttribute("createOrder", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @PostMapping("/addNewOrder")
    public String addNewOrder(HttpServletRequest request){

        String sender_name = request.getParameter("sender_name");
        String sender_phone = request.getParameter("sender_phone");
        String sender_city = request.getParameter("sender_city");
        String sender_street = request.getParameter("sender_street");

        Sender sender = new Sender(sender_name, sender_phone, sender_city, sender_street);
        this.senderService.save(sender);

        String receiver_name = request.getParameter("receiver_name");
        String receiver_phone = request.getParameter("receiver_phone");
        String receiver_city = request.getParameter("receiver_city");
        String receiver_street = request.getParameter("receiver_street");

        Receiver receiver = new Receiver(receiver_name, receiver_phone, receiver_city, receiver_street);
        this.receiverService.save(receiver);

        double price = Double.parseDouble(request.getParameter("price"));
        String comment = request.getParameter("comment");
        String pays1 = request.getParameter("is_paying");
        Pays pays = null;

        pays = pays1.equals("sender") ? Pays.SENDER : Pays.RECEIVER;

        Order order = new Order(sender, receiver, price, pays, comment, LocalDateTime.now(), this.userService.findByUsername(request.getRemoteUser()).get());
        this.orderService.save(order);

        request.getAttribute("name");
        return "redirect:/newOrders";
    }

    @GetMapping("/newOrders")
    public String getNewOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAllNew(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("newOrders", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @GetMapping("/activeOrders")
    public String getActiveOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAllActive(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("activeOrders", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @GetMapping("/deliveredOrders")
    public String getDeliveredOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAllDelivered(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("deliveredOrders", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @GetMapping("/returnedOrders")
    public String getReturnedOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAllReturned(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("returnedOrders", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @GetMapping("/canceledOrders")
    public String getCancelledOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", this.orderService.findAllCanceled(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("canceledOrders", true);
        model.addAttribute("username", request.getRemoteUser());
        return "orders";
    }

    @GetMapping("/financials")
    public String getMoney(Model model, HttpServletRequest request){
        model.addAttribute("money", this.orderService.getMoney(this.userService.findByUsername(request.getRemoteUser()).get()));
        model.addAttribute("username", request.getRemoteUser());
        return "financials";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Long id, Model model, HttpServletRequest request){
        model.addAttribute("order", this.orderService.findById(id).get());
        model.addAttribute("username", request.getRemoteUser());
        return "order";
    }
}
