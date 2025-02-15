package com.invento.invento.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private int employeeID;
    private int roleID;
    private int departmentID;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String position;
    private double salary;
    private String roleName;
    private String departmentName;

    public Employee(int employeeID, String email, String password) {
        this.employeeID = employeeID;
        this.email = email;
        this.password = password;
    }

    public Employee(int employeeID, int roleID, int departmentID, String name, String address, String phone, String email, String password, String position, double salary, String roleName, String departmentName) {
        this.employeeID = employeeID;
        this.roleID = roleID;
        this.departmentID = departmentID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.position = position;
        this.salary = salary;
        this.roleName = roleName;
        this.departmentName = departmentName;
    }
} 