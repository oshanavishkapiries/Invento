package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.dto.RoleDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.DepartmentService;
import com.invento.invento.service.custom.EmployeeService;
import com.invento.invento.service.custom.RoleService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
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

    private final DepartmentService departmentService;
    private final RoleService roleService;
    private final EmployeeService employeeService;

    public EmployeepopController() {
        this.departmentService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.DEPARTMENT);
        this.roleService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.ROLE);
        this.employeeService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.EMPLOYEE);
    }

    @FXML
    private void initialize() {
        try {
            loadDepartmentsAndRoles();
            setupEventHandlers();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    private void loadDepartmentsAndRoles() {
        departmentIdMap = departmentService.getAllDepartments().stream()
            .collect(Collectors.toMap(
                DepartmentDto::getName, 
                DepartmentDto::getDepartmentID, 
                (k, v) -> k, 
                LinkedHashMap::new
            ));

        roleIdMap = roleService.getAllRoles().stream()
            .collect(Collectors.toMap(
                RoleDto::getRoleName, 
                RoleDto::getRoleId, 
                (k, v) -> k, 
                LinkedHashMap::new
            ));

        department_combo.getItems().addAll(departmentIdMap.keySet());
        role_combo.getItems().addAll(roleIdMap.keySet());
    }

    private void setupEventHandlers() {
        done_btn.setOnAction(event -> {
            if (isUpdate) {
                update();
            } else {
                create();
            }
        });
    }

    private void create() {
        try {
            if (!formValidation()) {
                return;
            }

            EmployeeDto data = createEmployeeDto(0);
            employeeService.createEmployee(data);
            Reference.EmployeeViewController.refresh();
            AlertUtil.showSuccessAlert("Success", "Employee Created", "Employee has been created successfully");
            close();
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Employee Creation Error", e.getMessage());
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Unexpected Error", "An unexpected error occurred while creating employee");
        }
    }

    private void update() {
        try {
            if (!formValidation()) {
                return;
            }

            EmployeeDto data = createEmployeeDto(id);
            employeeService.updateEmployee(data);
            AlertUtil.showSuccessAlert("Success", "Employee Updated", "Employee has been updated successfully");
            close();
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Employee Update Error", e.getMessage());
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Unexpected Error", "An unexpected error occurred while updating employee");
        }
    }

    private EmployeeDto createEmployeeDto(int employeeId) {
        String selectedDepartment = department_combo.getSelectionModel().getSelectedItem();
        String selectedRole = role_combo.getSelectionModel().getSelectedItem();
        
        if (selectedDepartment == null || selectedRole == null) {
            throw new IllegalStateException("Department and Role must be selected");
        }

        int departmentId = departmentIdMap.get(selectedDepartment);
        int roleId = roleIdMap.get(selectedRole);

        return new EmployeeDto(
            employeeId,
            departmentId,
            roleId,
            __name.getText(),
            address.getText(),
            phone.getText(),
            email.getText(),
            password.getText(),
            position.getText(),
            Double.parseDouble(salary.getText()),
            selectedRole,
            selectedDepartment
        );
    }

    private boolean formValidation() {
        if (__name.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Name is required");
            return false;
        }
        if (email.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Email is required");
            return false;
        }
        if (password.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Password is required");
            return false;
        }
        if (conform_password.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Confirm password is required");
            return false;
        }
        if (!password.getText().equals(conform_password.getText())) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Password and confirm password do not match");
            return false;
        }
        if (address.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Address is required");
            return false;
        }
        if (department_combo.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Department is required");
            return false;
        }
        if (role_combo.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Role is required");
            return false;
        }
        if (salary.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Salary is required");
            return false;
        }
        if (position.getText().isEmpty()) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Position is required");
            return false;
        }
        
        try {
            Double.parseDouble(salary.getText());
        } catch (NumberFormatException e) {
            AlertUtil.showErrorAlert("Error", "Validation Error", "Salary must be a valid number");
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
        this.phone.setText(data.getPhone());
        this.position.setText(data.getPosition());
        this.salary.setText(String.valueOf(data.getSalary()));
        this.department_combo.getSelectionModel().select(data.getDepartmentName());
        this.role_combo.getSelectionModel().select(data.getRoleName());
    }

    public void viewDetail(EmployeeDto data) {
        setUpdate(data.getEmployeeID(), data);
        password.setVisible(false);
        conform_password.setVisible(false);
        pass_label.setVisible(false);
        pass_label_conform.setVisible(false);
        done_btn.setVisible(false);
    }
}
