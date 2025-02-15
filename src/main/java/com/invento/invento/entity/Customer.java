package com.invento.invento.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int customerID;
    private String name;
    private String phone;
    private String email;
    private String address;
} 