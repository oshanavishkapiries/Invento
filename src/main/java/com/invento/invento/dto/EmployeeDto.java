package com.invento.invento.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
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

    public EmployeeDto(int employeeID, int roleID, int departmentID, String name, String address, String phone, String email, String password, String position, double salary, String roleName, String departmentName) {

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

    public EmployeeDto(int employeeID,String email, String password) {
        this.email = email;
        this.password = password;
        this.employeeID = employeeID;
    }


}