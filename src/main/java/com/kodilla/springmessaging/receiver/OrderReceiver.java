package com.kodilla.springmessaging.receiver;

import com.kodilla.springmessaging.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OrderReceiver {
    @JmsListener(containerFactory = "jmsFactory", destination = "jms-queue")
    public void receive(Order order) throws InterruptedException {
        createOrder(order);
    }

    private void createOrder(Order order) throws InterruptedException {
        System.out.println("Order creating: " + order.getOrderTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = LocalDateTime.now().format(formatter);
        System.out.println("Order execution: " + timeNow);
        System.out.printf("Customer: %s, Dish name: %s\n", order.getName(), order.getDishName());
        System.out.print("Processing.");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println();
        System.out.println("Completed\n");
    }
}
