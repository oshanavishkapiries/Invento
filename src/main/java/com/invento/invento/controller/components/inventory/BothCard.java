package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class BothCard {

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private ImageView image;

    @FXML
    private Label tag;

    @FXML
    private Label brand;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label qty;

    private int Id;

    public void initialize() {
        btn_delete.setOnAction(event -> showDeleteConfirmationAlert());
    }

    private void showDeleteConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Product?");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteProduct();
        }
    }

    private void deleteProduct() {
        boolean isDeleted = ProductModel.deleteProductById(Id);
        if (isDeleted) {
            System.out.println("Product deleted successfully!");
            Reference.gridView.initialize();
            Reference.listView.initialize();
            new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully!").showAndWait();
        } else {
            System.out.println("Failed to delete the product.");
            new Alert(Alert.AlertType.ERROR, "Failed to delete the product.").showAndWait();
        }
    }

    public void setData(inventoryCardDto cardData) {
        Id = cardData.getId();
        name.setText(cardData.getName());
        brand.setText(cardData.getBrand());
        tag.setText(cardData.getTag());
        price.setText(String.valueOf(cardData.getPrice()));
        qty.setText(String.valueOf(cardData.getQuantity()));
        //image.setImage(new Image(cardData.getImageUrl()));
    }
}
