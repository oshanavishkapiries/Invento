package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class inventoryCardDto {

    private String name;
    private String description;
    private String imageUrl;
    private String brand;
    private String tag;
    private double price;
    private int quantity;
    private int id;

    public inventoryCardDto(int id, String imageUrl, String description, String name, String brand, String tag, double price, int quantity) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.description = description;
        this.name = name;
        this.brand = brand;
        this.tag = tag;
        this.price = price;
        this.quantity = quantity;
    }
}