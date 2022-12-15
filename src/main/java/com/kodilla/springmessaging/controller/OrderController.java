package com.kodilla.springmessaging.controller;

import com.kodilla.springmessaging.model.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // Sample call: localhost:8080/order/create?name=michal&dish=pizza
    @GetMapping("/create")
    public void create(@RequestParam String name, @RequestParam String dish){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String orderTime = LocalDateTime.now().format(formatter);
        jmsTemplate.convertAndSend(JMS_QUEUE_NAME, new Order(name, dish, orderTime));
    }
}
