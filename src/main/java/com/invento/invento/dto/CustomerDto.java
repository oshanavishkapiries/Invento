package com.invento.invento.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private int customerId;
    private String name;
    private String phone;
    private String email;
    private String address;


    public CustomerDto(int customerId, String name, String phone, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
