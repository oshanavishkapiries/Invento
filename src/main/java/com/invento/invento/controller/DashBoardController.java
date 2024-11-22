package com.invento.invento.controller;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.utils.Reference;

public class DashBoardController {
   
    public void intialize(){
        Reference.DashBoardController = this;
    }

    public void setUserdetails(EmployeeDto employeeDto) {
    }
}
