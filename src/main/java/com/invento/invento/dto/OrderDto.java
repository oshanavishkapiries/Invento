package com.invento.invento.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private int orderID;
    private int customerID;
    private String orderDate;
    private double totalAmount;

    public OrderDto(int orderID, int customerID, String orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
}