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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    public EmployeeViewController() {
        this.employeeService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.EMPLOYEE);
        this.departmentService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.DEPARTMENT);
        this.roleService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.ROLE);
    }

    @FXML
    public void initialize() {
        Reference.EmployeeViewController = this;
        init();
        try {
            populate(employeeService.getAllEmployees());
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public void refresh() {
        try {
            populate(employeeService.getAllEmployees());
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    private void init() {
        try {
            setupSearchField();
            setupAddButton();
            setupDepartmentSwitch();
            setupRoleSwitch();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    private void setupSearchField() {
        search_input.setOnKeyTyped(e -> {
            try {
                populate(employeeService.getAllEmployeesByName(search_input.getText()));
            } catch (SQLException ex) {
                AlertUtil.showErrorAlert("Error", "Search Error", ex.getMessage());
            }
        });
    }

    private void setupAddButton() {
        add_btn.setOnAction(e -> popup());
    }

    private void setupDepartmentSwitch() {
        Department_switch.getItems().add("Department");
        List<String> departmentNames = departmentService.getAllDepartments().stream()
                .map(DepartmentDto::getName)
                .collect(Collectors.toList());
        Department_switch.getItems().addAll(departmentNames);
        Department_switch.getSelectionModel().selectFirst();

        Department_switch.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            onDepartmentSwitch(newValue);
        });
    }

    private void setupRoleSwitch() {
        role_switch.getItems().add("Roles");
        List<String> roleNames = roleService.getAllRoles().stream()
                .map(RoleDto::getRoleName)
                .collect(Collectors.toList());
        role_switch.getItems().addAll(roleNames);
        role_switch.getSelectionModel().selectFirst();

        role_switch.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            onRoleSwitch(newValue);
        });
    }

    public void populate(List<EmployeeDto> employees) {
        try {
            listView.getChildren().clear();
            for (EmployeeDto employee : employees) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/employee/EmployeeCard.fxml"));
                AnchorPane empcard = loader.load();
                EmployeeCard controller = loader.getController();
                controller.setData(employee);
                listView.getChildren().add(empcard);
            }
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public void delete_by_id(int id) {
        try {
            if (employeeService.deleteEmployee(id)) {
                AlertUtil.showAlert("Success", "Employee Deleted", "Employee deleted successfully.");
                populate(employeeService.getAllEmployees());
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
                populate(employeeService.getAllEmployees());
            } else {
                populate(employeeService.getAllEmployeesByDepartment(newValue));
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    private void onRoleSwitch(String newValue) {
        try {
            if (newValue.equals("Roles")) {
                populate(employeeService.getAllEmployees());
            } else {
                populate(employeeService.getAllEmployeesByRoleName(newValue));
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public EmployeepopController popup() {
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
            AlertUtil.showErrorAlert("Error", "Loading Error", "Failed to open employee popup: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
