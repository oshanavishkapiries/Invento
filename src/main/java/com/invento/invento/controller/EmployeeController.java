package com.invento.invento.controller;

import com.invento.invento.utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EmployeeController {

    @FXML
    private BorderPane department_view;

    @FXML
    private BorderPane employerview;

    @FXML
    private BorderPane role_view;

    public void initialize() {
        init();
    }

    public void init() {
        try {
            AnchorPane RoleView = FXMLLoader.load(getClass().getResource("/view/components/employee/RoleView.fxml"));
            AnchorPane DepartmentView = FXMLLoader.load(getClass().getResource("/view/components/employee/DepartmentView.fxml"));
            AnchorPane EmployerView = FXMLLoader.load(getClass().getResource("/view/components/employee/EmployeeView.fxml"));

            role_view.setCenter(RoleView);
            department_view.setCenter(DepartmentView);
            employerview.setCenter(EmployerView);

        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }


}
