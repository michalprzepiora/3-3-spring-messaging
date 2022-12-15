package com.kodilla.springmessaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Order implements Serializable {
    private String name;
    private String dishName;
    private String orderTime;
}
