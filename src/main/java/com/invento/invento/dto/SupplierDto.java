package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
    private int supplierID;
    private String name;
    private String phone;
    private String email;
    private String address;

    public SupplierDto(int supplierID, String name, String phone, String email, String address) {
        this.supplierID = supplierID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
