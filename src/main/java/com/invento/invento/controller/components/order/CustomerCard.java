package com.invento.invento.controller.components.order;

import com.invento.invento.dto.CustomerDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.CustomerService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class CustomerCard {

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Label email;

    @FXML
    private Label name_;

    @FXML
    private Label phone;

    @FXML
    private Label address;

    private int Id;

    private final CustomerService customerService;

    public CustomerCard() {
        this.customerService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    }

    @FXML
    private void initialize() {
        btn_edit.setOnAction(e -> onEditClick());
        btn_delete.setOnAction(e -> onDeleteClick());
    }

    private void onEditClick() {
        Reference.OrderController.customer_update_data_set_load(Id);
    }

    private void onDeleteClick() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION, 
            "Are you sure you want to delete this customer?", 
            ButtonType.YES, ButtonType.NO);
        
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                deleteCustomer();
            }
        });
    }

    private void deleteCustomer() {
        try {
            if (customerService.deleteCustomer(Id)) {
                AlertUtil.showAlert("Success", "Customer Deleted", 
                    "Customer deleted successfully.");
                Reference.OrderController.populateCustomerListView();
            } else {
                AlertUtil.showErrorAlert("Error", "Deletion Error", 
                    "Failed to delete customer.");
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Database Error", e.getMessage());
        }
    }

    public void setCustomer(CustomerDto customer) {
        this.Id = customer.getCustomerID();
        this.email.setText(customer.getEmail());
        this.name_.setText(customer.getName());
        this.phone.setText(customer.getPhone());
        this.address.setText(customer.getAddress());
    }
}
