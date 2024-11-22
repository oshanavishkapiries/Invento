package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.dto.RoleDto;
import com.invento.invento.model.DepartmentModel;
import com.invento.invento.model.EmployeeModel;
import com.invento.invento.model.RoleModel;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeepopController {

    @FXML
    private TextArea address;

    @FXML
    private TextField conform_password;

    @FXML
    private ComboBox<String> department_combo;

    @FXML
    private Button done_btn;

    @FXML
    private TextField email;

    @FXML
    private TextField __name;

    @FXML
    private TextField password;

    @FXML
    private TextField position;

    @FXML
    private ComboBox<String> role_combo;

    @FXML
    private TextField salary;

    @FXML
    private TextField phone;

    @FXML
    private Label pass_label;

    @FXML
    private Label pass_label_conform;


    private boolean isUpdate = false;
    private int id = -1;
    private Map<String, Integer> departmentIdMap;
    private Map<String, Integer> roleIdMap;

    @FXML
    private void initialize() {
        try {

            departmentIdMap = DepartmentModel.getAllDepartments().stream().collect(Collectors.toMap(DepartmentDto::getName, DepartmentDto::getDepartmentID, (k, v) -> k, LinkedHashMap::new));

            roleIdMap = RoleModel.getAllRoles().stream().collect(Collectors.toMap(RoleDto::getRoleName, RoleDto::getRoleId, (k, v) -> k, LinkedHashMap::new));

            department_combo.getItems().addAll(departmentIdMap.keySet());
            role_combo.getItems().addAll(roleIdMap.keySet());

            done_btn.setOnAction(event -> {
                if (isUpdate) {
                    update();
                } else {
                    create();
                }
            });

        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    private void create() {
        try {
            if (formValidation()) {
                int departmentId = departmentIdMap.get(department_combo.getSelectionModel().getSelectedItem());
                int roleId = roleIdMap.get(role_combo.getSelectionModel().getSelectedItem());

                EmployeeDto data = new EmployeeDto(0, departmentId, roleId, __name.getText(), address.getText(), phone.getText(), email.getText(), password.getText(), position.getText(), Double.parseDouble(salary.getText()), "", "");
                EmployeeModel.createEmployee(data);
                Reference.EmployeeViewController.refresh();
                AlertUtil.showSuccessAlert("Success", "Employee Created", "");
                close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Employee Creation Error", e.getMessage());
        }
    }

    private void update() {
        try {
            if (formValidation()) {
                int departmentId = departmentIdMap.get(department_combo.getSelectionModel().getSelectedItem());
                int roleId = roleIdMap.get(role_combo.getSelectionModel().getSelectedItem());

                EmployeeDto data = new EmployeeDto(id, departmentId, roleId, __name.getText(), address.getText(), phone.getText(), email.getText(), password.getText(), position.getText(), Double.parseDouble(salary.getText()), "", "");
                EmployeeModel.updateEmployee(data);
                AlertUtil.showSuccessAlert("Success", "Employee Updated", "");
                close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Employee Update Error", e.getMessage());
        }
    }

    private boolean formValidation() {
        if (__name.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Name is required", "");
            return false;
        }
        if (email.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Email is required", "");
            return false;
        }
        if (password.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Password is required", "");
            return false;
        }
        if (conform_password.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Confirm password is required", "");
            return false;
        }
        if (!password.getText().equals(conform_password.getText())) {
            AlertUtil.showErrorAlert("Error", "Password and confirm password do not match", "");
            return false;
        }
        if (address.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Address is required", "");
            return false;
        }
        if (department_combo.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showErrorAlert("Error", "Department is required", "");
            return false;
        }
        if (role_combo.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showErrorAlert("Error", "Role is required", "");
            return false;
        }
        if (salary.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Salary is required", "");
            return false;
        }
        if (position.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Position is required", "");
            return false;
        }
        return true;
    }

    private void close() {
        ((Stage) done_btn.getScene().getWindow()).close();
    }

    public void setUpdate(int id, EmployeeDto data) {
        this.id = id;
        this.isUpdate = true;
        this.__name.setText(data.getName());
        this.email.setText(data.getEmail());
        this.address.setText(data.getAddress());
        this.department_combo.getSelectionModel().select(data.getDepartmentName());
        this.role_combo.getSelectionModel().select(data.getRoleName());
        this.salary.setText(String.valueOf(data.getSalary()));
        this.position.setText(data.getPosition());
    }

    public void viewDetail(EmployeeDto data) {
        this.__name.setText(data.getName());
        this.email.setText(data.getEmail());
        this.phone.setText(data.getPhone());
        this.address.setText(data.getAddress());
        this.department_combo.getSelectionModel().select(data.getDepartmentName());
        this.role_combo.getSelectionModel().select(data.getRoleName());
        this.salary.setText(String.valueOf(data.getSalary()));
        this.position.setText(data.getPosition());
        this.password.setVisible(false);
        this.conform_password.setVisible(false);
        this.pass_label.setVisible(false);
        this.pass_label_conform.setVisible(false);
        this.done_btn.setVisible(false);
    }
}
