package com.invento.invento.controller;

import com.invento.invento.controller.components.order.CustomerCard;
import com.invento.invento.dto.CustomerDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.CustomerService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderController {

    @FXML
    private VBox customerListView;

    @FXML
    private TextArea customer_input_address;

    @FXML
    private Button customer_input_done_btn;

    @FXML
    private TextField customer_input_email;

    @FXML
    private TextField customer_input_name;

    @FXML
    private TextField customer_input_phone;

    @FXML
    private BorderPane place_Order;

    private int temp_id;

    private final CustomerService customerService;

    public OrderController() {
        this.customerService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    }

    @FXML
    public void initialize() {
        try {
            AnchorPane PlaceOrder = FXMLLoader.load(getClass().getResource("/view/components/order/PlaceOredr.fxml"));
            place_Order.setCenter(PlaceOrder);
            Reference.OrderController = this;
            populateCustomerListView();
            customer_input_done_btn.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    @FXML
    public void customer_input_name_onchange(KeyEvent event) {
        customer_input_done_btn.setDisable(customer_input_name.getText().isEmpty());
    }

    public void customer_update_data_set_load(int Id) {
        try {
            CustomerDto customer = customerService.getCustomerById(Id);
            if (customer != null) {
                this.temp_id = Id;
                customer_input_name.setText(customer.getName());
                customer_input_phone.setText(customer.getPhone());
                customer_input_email.setText(customer.getEmail());
                customer_input_address.setText(customer.getAddress());
                customer_input_done_btn.setText("Update");
                customer_input_done_btn.setDisable(false);
            } else {
                AlertUtil.showErrorAlert("Error", "Customer Not Found", "Unable to find customer with ID: " + Id);
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Database Error", e.getMessage());
        }
    }

    @FXML
    void customer_search_onchange(KeyEvent event) {
        try {
            TextField searchField = (TextField) event.getSource();
            String searchText = searchField.getText();

            customerListView.getChildren().clear();
            List<CustomerDto> customers = customerService.searchCustomersByName(searchText);

            for (CustomerDto customer : customers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/order/CustomerCard.fxml"));
                AnchorPane card = loader.load();
                CustomerCard controller = loader.getController();
                controller.setCustomer(customer);
                customerListView.getChildren().add(card);
            }
        } catch (SQLException | IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    @FXML
    public void customer_done_onclick(MouseEvent event) {
        String name = customer_input_name.getText();
        String phone = customer_input_phone.getText();
        String email = customer_input_email.getText();
        String address = customer_input_address.getText();

        try {
            if (customer_input_done_btn.getText().equals("Update")) {
                CustomerDto customerDto = new CustomerDto(temp_id, name, phone, email, address);
                if (customerService.updateCustomer(customerDto)) {
                    AlertUtil.showAlert("Success", "Customer Updated", "Customer updated successfully.");
                    clearForm();
                    populateCustomerListView();
                } else {
                    AlertUtil.showErrorAlert("Error", "Update Error", "Failed to update customer.");
                }
                return;
            }

            if (customerService.isEmailExist(email)) {
                AlertUtil.showErrorAlert("Error", "Email Error", "Email already exists.");
                return;
            }

            CustomerDto newCustomer = new CustomerDto(0, name, phone, email, address);
            if (customerService.createCustomer(newCustomer)) {
                AlertUtil.showAlert("Success", "Customer Added", "Customer added successfully.");
                clearForm();
                populateCustomerListView();
            } else {
                AlertUtil.showErrorAlert("Error", "Customer Error", "Failed to add customer.");
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert("Error", "Database Error", e.getMessage());
        }
    }

    private void clearForm() {
        customer_input_name.clear();
        customer_input_phone.clear();
        customer_input_email.clear();
        customer_input_address.clear();
        customer_input_done_btn.setDisable(true);
        customer_input_done_btn.setText("Create");
        temp_id = 0;
    }

    @FXML
    public void populateCustomerListView() {
        try {
            customerListView.getChildren().clear();
            List<CustomerDto> customers = customerService.getAllCustomers();

            for (CustomerDto customer : customers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/order/CustomerCard.fxml"));
                AnchorPane card = loader.load();
                CustomerCard controller = loader.getController();
                controller.setCustomer(customer);
                customerListView.getChildren().add(card);
            }
        } catch (SQLException | IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }
}
