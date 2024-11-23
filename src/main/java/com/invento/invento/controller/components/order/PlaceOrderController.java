package com.invento.invento.controller.components.order;

import com.invento.invento.dto.OrderDto;
import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.OrderModel;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController {

    @FXML
    private Label Item_count;

    @FXML
    private VBox buy_product_list_view;

    @FXML
    private Button cancel_order;

    @FXML
    private Button place_order;

    @FXML
    private Button print_order;

    @FXML
    private VBox product_item_list_view;

    @FXML
    private TextField product_search_input;

    @FXML
    private TextField set_discount;

    @FXML
    private Label total_prize;

    public List<inventoryCardDto> selectedBuyingProduct = new ArrayList<>();


    @FXML
    public void initialize() {
        Reference.PlaceOrderController = this;

        init();

        try {
            populateProductItemListView(ProductModel.getAllProducts());
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }

    }


    public void init() {
        product_search_input.setOnKeyTyped(event -> {
            try {
                populateProductItemListView(ProductModel.searchProductByName(product_search_input.getText()));
            } catch (Exception e) {
                AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
            }
        });


        cancel_order.setOnAction(event -> {
            selectedBuyingProduct.clear();
            populateBuyProductListView();
            Item_count.setText("0");
            total_prize.setText("0");
        });

        set_discount.setOnKeyTyped(event -> {
            double discount = Double.parseDouble(set_discount.getText());
            double total = 0;
            for (inventoryCardDto product : selectedBuyingProduct) {
                total += product.getPrice() * (1 - discount);
            }
            total_prize.setText(String.valueOf(total));
        });

        place_order.setOnAction(event -> {
            if (selectedBuyingProduct.size() > 0) {
                try {
                    String date = java.time.LocalDate.now().toString();
                    if (OrderModel.saveOrder(new OrderDto(0, 1, date, totalPrize()), selectedBuyingProduct)) {
                        AlertUtil.showSuccessAlert("Success", "Order Saved", "Order Saved Successfully");
                        selectedBuyingProduct.clear();
                        populateBuyProductListView();
                        Item_count.setText("0");
                        total_prize.setText("0");
                    } else {
                        AlertUtil.showErrorAlert("Error", "Saving Error", "Order Saving Failed");
                    }
                } catch (Exception e) {
                    AlertUtil.showErrorAlert("Error", "Saving Error", e.getMessage());
                }
            } else {
                AlertUtil.showErrorAlert("Error", "No Products Selected", "Please select at least one product to save the order");
            }
        });


    }

    @FXML
    public void populateProductItemListView(List<inventoryCardDto> products) {
        product_item_list_view.getChildren().clear();
        for (inventoryCardDto product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/order/Order_ProductList_card.fxml"));
                AnchorPane card = loader.load();
                OrderProductListCard controller = loader.getController();
                controller.setData(product);
                product_item_list_view.getChildren().add(card);
            } catch (IOException e) {
                AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
            }
        }
    }


    public void populateBuyProductListView() {
        buy_product_list_view.getChildren().clear();
        for (inventoryCardDto product : selectedBuyingProduct) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/order/Order_list_card.fxml"));
                AnchorPane card = loader.load();
                OrderListCard controller = loader.getController();
                controller.setData(product);
                buy_product_list_view.getChildren().add(card);
            } catch (IOException e) {
                AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
            }
        }

        Item_count.setText(String.valueOf(selectedBuyingProduct.size()));
        double total = 0;
        for (inventoryCardDto product : selectedBuyingProduct) {
            total += product.getPrice() * product.getQuantity();
        }
        total_prize.setText(String.valueOf(total));
    }

    public double totalPrize() {
        double total = 0;
        for (inventoryCardDto product : selectedBuyingProduct) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public void addBuyingProduct(inventoryCardDto product) {
        product.setQuantity(1);
        selectedBuyingProduct.add(product);
        populateBuyProductListView();
    }


    public void removeBuyingProduct(inventoryCardDto product) {
        selectedBuyingProduct.remove(product);
        populateBuyProductListView();
    }

}