package com.kodilla.springmessaging.controller;

import com.kodilla.springmessaging.model.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String JMS_QUEUE_NAME = "jms-queue";
    private JmsTemplate jmsTemplate;

    public OrderController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // Sample POST call: localhost:8080/order?name=michal&dish=pizza
    @PostMapping()
    public void create(@RequestParam String name, @RequestParam String dish){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String orderTime = LocalDateTime.now().format(formatter);
        jmsTemplate.convertAndSend(JMS_QUEUE_NAME, new Order(name, dish, orderTime));
    }
}
