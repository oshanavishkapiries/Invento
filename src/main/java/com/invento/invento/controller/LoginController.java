package com.invento.invento.controller;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.model.EmployeeModel;
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

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        try {
            EmployeeDto employeeDto = EmployeeModel.getEmployeeByEmail(txtEmail.getText());
            if (employeeDto == null) {
                AlertUtil.showAlert("Error", "Auth fail","Employee not found ");
                return;
            }

            if (employeeDto.getPassword().equals(txtPassword.getText())) {
                ViewNavigator.loadView("layouts/dashLayout",true);
                AlertUtil.showAlert("Success", "Login successful","Login successful");
                Reference.DashBoardController.setUserdetails(employeeDto);
                return;
            }

            AlertUtil.showAlert("Error", "Auth fail","Wrong password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
