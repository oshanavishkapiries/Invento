package com.invento.invento.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private int customerID;
    private String name;
    private String phone;
    private String email;
    private String address;


    public CustomerDto(int customerID, String name, String phone, String email, String address) {
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
