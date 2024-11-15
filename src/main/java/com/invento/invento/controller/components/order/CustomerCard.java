package com.invento.invento.controller.components.order;

import com.invento.invento.dto.CustomerDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CustomerCard {

    @FXML
    private Label address;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phone;


    public void setCustomer(CustomerDto customer) {
        this.address.setText(customer.getAddress());
        this.email.setText(customer.getEmail());
        this.name.setText(customer.getName());
        this.phone.setText(customer.getPhone());
    }

}
