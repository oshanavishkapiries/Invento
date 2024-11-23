package com.invento.invento.controller.components.order;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrderListCard {

    @FXML
    private Button buy_p_delete;

    @FXML
    private Button buy_p_minus;

    @FXML
    private Label buy_p_name;

    @FXML
    private Button buy_p_plus;

    @FXML
    private Label buy_p_prize;

    @FXML
    private TextField buy_p_qty_input;

    @FXML
    private Label total_prize;

    private inventoryCardDto product;

    @FXML
    private void initialize() {

        buy_p_delete.setOnAction(event -> {
            Reference.PlaceOrderController.removeBuyingProduct(product);
            Reference.PlaceOrderController.populateBuyProductListView();
        });


        buy_p_minus.setOnAction(event -> {
            int quantity = parseQuantity();
            if (quantity > 0) {
                quantity--;
                updateQuantity(quantity);
            }
        });


        buy_p_plus.setOnAction(event -> {
            int quantity = parseQuantity();
            quantity++;
            updateQuantity(quantity);
        });
    }

    public void setData(inventoryCardDto product) {
        this.product = product;
        buy_p_name.setText(product.getName());
        buy_p_prize.setText(String.format("%.2f", product.getPrice()));
        buy_p_qty_input.setText(String.valueOf(product.getQuantity()));
        total_prize.setText(String.format("%.2f", product.getPrice() * product.getQuantity()));
    }

    private int parseQuantity() {
        try {
            return Integer.parseInt(buy_p_qty_input.getText());
        } catch (NumberFormatException e) {
            buy_p_qty_input.setText("0");
            return 0;
        }
    }

    private void updateQuantity(int quantity) {
        product.setQuantity(quantity);
        buy_p_qty_input.setText(String.valueOf(quantity));
        total_prize.setText(String.format("%.2f", product.getPrice() * quantity));

        int index = Reference.PlaceOrderController.selectedBuyingProduct.indexOf(product);
        if (index >= 0) {
            Reference.PlaceOrderController.selectedBuyingProduct.get(index).setQuantity(quantity);
        }
        Reference.PlaceOrderController.populateBuyProductListView();


    }
}