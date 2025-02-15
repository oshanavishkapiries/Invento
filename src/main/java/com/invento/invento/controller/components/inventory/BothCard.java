package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.ProductService;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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

    private final ProductService productService;

    public BothCard() {
        this.productService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.PRODUCT);
    }

    public void initialize() {
        btn_delete.setOnAction(event -> showDeleteConfirmationAlert());
        btn_edit.setOnAction(event -> btn_edit_click());
    }

    @FXML
    void card_on_click(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/ProductView.fxml"));
            AnchorPane root = loader.load();
            ProductView productViewController = loader.getController();
            productViewController.init(Id);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Product Details");
            stage.getIcons().add(new Image(getClass().getResource("/view/assets/icons/product.png").toString()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_edit_click() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/UpdatePopup.fxml"));
            AnchorPane root = loader.load();

            UpdatePopup updatePopupController = loader.getController();
            updatePopupController.initialize(Id);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            Reference.UpdatePopupScene = stage;
            stage.setScene(scene);
            stage.setTitle("Update Product");
            stage.getIcons().add(new Image(getClass().getResource("/view/assets/icons/product.png").toString()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        boolean isDeleted = productService.deleteProductById(Id);
        if (isDeleted) {
            Reference.gridView.initialize();
            Reference.listView.initialize();
            new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the product.").showAndWait();
        }
    }

    public void setData(inventoryCardDto cardData) {
        Id = cardData.getId();
        name.setText(cardData.getName());
        price.setText(String.valueOf(cardData.getPrice()));
        qty.setText(String.valueOf(cardData.getQuantity()));
        setImage(cardData.getImageUrl());
    }

    public void setImage(String path) {
        String absolutePath = new File(path).getAbsolutePath();
        Image image = new Image("file:" + absolutePath);

        if (path.isEmpty()) {
            image = new Image(getClass().getResourceAsStream("/view/assets/icons/no_pic.png"));
        }
        this.image.setImage(image);
    }
}


