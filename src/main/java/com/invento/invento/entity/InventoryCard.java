package com.invento.invento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCard {
    private int id;
    private String imageUrl;
    private String description;
    private String name;
    private String brand;
    private String tag;
    private double price;
    private int quantity;
} 