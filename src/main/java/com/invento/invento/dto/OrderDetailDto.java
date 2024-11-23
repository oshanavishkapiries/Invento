package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private int orderDetailID;
    private int orderID;
    private int productID;
    private int quantity;
    private double price;

    public OrderDetailDto(int orderDetailID, int orderID, int productID, int quantity, double price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }
}