package com.invento.invento.controller;

import com.invento.invento.controller.components.order.CustomerCard;
import com.invento.invento.dto.CustomerDto;
import com.invento.invento.model.CustomerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderController {


    @FXML
    private TextArea address;

    @FXML
    private VBox customerListView;

    @FXML
    private Button done_btn;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;


    @FXML
    public void initialize() {
        populateCustomerListView();
        done_btn.setDisable(true);
    }

    @FXML
    private void populateCustomerListView() {
        try {
            List<CustomerDto> customers = CustomerModel.getAllCustomers();

            for (CustomerDto customer : customers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/order/CustomerCard.fxml"));
                AnchorPane card = loader.load();
                CustomerCard controller = loader.getController();
                controller.setCustomer(customer);
                customerListView.getChildren().add(card);

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void search_customer_onchange(KeyEvent event) {

    }
}
