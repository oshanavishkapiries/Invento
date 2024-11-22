package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.model.DepartmentModel;
import com.invento.invento.model.EmployeeModel;
import com.invento.invento.model.RoleModel;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.dto.RoleDto;
import javafx.stage.Stage;

public class EmployeeViewController {

    @FXML
    private ComboBox<String> Department_switch;

    @FXML
    private ComboBox<String> role_switch;

    @FXML
    private Button add_btn;

    @FXML
    private VBox listView;

    @FXML
    private TextField search_input;


    @FXML
    private void initialize() {
        Reference.EmployeeViewController = this;
        init();
        try {
            populate(EmployeeModel.getAllEmployees());
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public void refresh() {
        try {
            populate(EmployeeModel.getAllEmployees());
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    private void init() {
        try {
            search_input.setOnKeyTyped(e -> {
                try {
                    populate(EmployeeModel.getAllEmployeesByName(search_input.getText()));
                } catch (SQLException ex) {
                    AlertUtil.showErrorAlert("Error", "Loading Error", ex.getMessage());
                }
            });

            add_btn.setOnAction(e -> {
                    popup();
            });

            Department_switch.getItems().add("Department");
            List<String> departmentNames = DepartmentModel.getAllDepartments().stream()
                    .map(DepartmentDto::getName)
                    .collect(Collectors.toList());
            Department_switch.getItems().addAll(departmentNames);
            Department_switch.getSelectionModel().selectFirst();

            Department_switch.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                onDepartmentSwitch(newValue);
            });

            role_switch.getItems().add("Roles");
            List<String> roleNames = RoleModel.getAllRoles().stream()
                    .map(RoleDto::getRoleName)
                    .collect(Collectors.toList());
            role_switch.getItems().addAll(roleNames);
            role_switch.getSelectionModel().selectFirst();

            role_switch.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                onRoleSwitch(newValue);
            });


        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }

    }


    public void populate(List<EmployeeDto> cards) {
        try {
            listView.getChildren().clear();
            for (EmployeeDto card : cards) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/employee/EmployeeCard.fxml"));
                AnchorPane empcard = loader.load();
                EmployeeCard controller = loader.getController();
                controller.setData(card);
                listView.getChildren().add(empcard);
            }
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }


    public void delete_by_id(int id) {
        try {
            if (EmployeeModel.deleteEmployee(id)) {
                AlertUtil.showAlert("Success", "Employee Deleted", "Employee deleted successfully.");
                populate(EmployeeModel.getAllEmployees());
            } else {
                AlertUtil.showErrorAlert("Error", "Deletion Error", "Failed to delete employee.");
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Database Error", e.getMessage());
        }
    }

    private void onDepartmentSwitch(String newValue) {
        try {
            if (newValue.equals("Department")) {
                populate(EmployeeModel.getAllEmployees());
            } else {
                try {
                    populate(EmployeeModel.getAllEmployeesByDepartment(newValue));
                } catch (SQLException e) {
                    e.printStackTrace();
                    AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    private void onRoleSwitch(String newValue) {
        try {
            if (newValue.equals("Roles")) {
                populate(EmployeeModel.getAllEmployees());
            } else {
                try {
                    populate(EmployeeModel.getAllEmployeesByRoleName(newValue));
                } catch (SQLException e) {
                    e.printStackTrace();
                    AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public EmployeepopController popup(){
        try {
            FXMLLoader loader = new FXMLLoader(EmployeepopController.class.getResource("/view/components/employee/Employeepop.fxml"));
            Parent root = loader.load();
            EmployeepopController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Employee");
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/view/assets/icons/emp.png").toString()));
            stage.setScene(new Scene(root));
            stage.show();

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
