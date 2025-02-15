package com.invento.invento.controller;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.EmployeeService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import com.invento.invento.utils.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private final EmployeeService employeeService;

    public LoginController() {
        this.employeeService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.EMPLOYEE);
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        try {
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            if (email.isEmpty() || password.isEmpty()) {
                AlertUtil.showAlert("Error", "Auth fail", "Please enter both email and password");
                return;
            }

            EmployeeDto employeeDto = employeeService.getEmployeeByEmail(email);
            if (employeeDto == null) {
                AlertUtil.showAlert("Error", "Auth fail", "Employee not found");
                return;
            }

            if (employeeDto.getPassword().equals(password)) {
                ViewNavigator.loadView("layouts/dashLayout", true);
                AlertUtil.showAlert("Success", "Login successful", "Login successful");
                Reference.DashBoardController.setUserdetails(employeeDto);
                return;
            }

            AlertUtil.showAlert("Error", "Auth fail", "Wrong password");
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Database Error", e.getMessage());
            e.printStackTrace();
        }
    }
}
