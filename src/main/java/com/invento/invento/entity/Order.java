package com.invento.invento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderID;
    private int customerID;
    private String orderDate;
    private double totalAmount;
} 