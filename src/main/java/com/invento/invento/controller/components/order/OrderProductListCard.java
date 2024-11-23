package com.invento.invento.controller.components.order;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderProductListCard {

    @FXML
    private Button p_add_btn;

    @FXML
    private Label p_brand;

    @FXML
    private Label p_name;

    private inventoryCardDto Productdto;


    @FXML
    public void initialize() {
        p_add_btn.setOnAction(event -> {
            Reference.PlaceOrderController.addBuyingProduct(Productdto);
        });
    }


    public void setData(inventoryCardDto product) {
        this.Productdto = product;
        p_name.setText(product.getName());
        p_brand.setText(product.getBrand());
    }
}

