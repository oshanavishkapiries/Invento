package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.EmployeeService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class EmployeeCard {
    @FXML
    private Button delete;

    @FXML
    private Label department;

    @FXML
    private Button edit;

    @FXML
    private Label email;

    @FXML
    private Label __name;

    @FXML
    private Label phone;

    @FXML
    private Label role;

    private int id;

    private final EmployeeService employeeService;

    public EmployeeCard() {
        this.employeeService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.EMPLOYEE);
    }

    @FXML
    private void initialize() {
        delete.setOnAction(event -> {
            Reference.EmployeeViewController.delete_by_id(id);
        });
        
        edit.setOnAction(event -> {
            try {
                Reference.EmployeeViewController.popup()
                    .setUpdate(id, employeeService.getEmployeeById(id));
            } catch (SQLException e) {
                e.printStackTrace();
                AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
            }
        });
        
        __name.setOnMouseClicked(event -> {
            try {
                Reference.EmployeeViewController.popup()
                    .viewDetail(employeeService.getEmployeeById(id));
            } catch (SQLException e) {
                e.printStackTrace();
                AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
            }
        });
    }

    public void setData(EmployeeDto data) {
        this.id = data.getEmployeeID();
        this.__name.setText(data.getName());
        this.email.setText(data.getEmail());
        this.phone.setText(data.getPhone());
        this.department.setText(data.getDepartmentName());
        this.role.setText(data.getRoleName());
    }
}
