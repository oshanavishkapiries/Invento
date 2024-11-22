package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private int departmentID;
    private String name;
    private String location;

    public DepartmentDto(int departmentID, String name, String location) {
        this.departmentID = departmentID;
        this.name = name;
        this.location = location;
    }
}