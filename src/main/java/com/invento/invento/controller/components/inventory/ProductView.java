package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class ProductView {
    @FXML
    private Label brand_label;

    @FXML
    private Label description_label;

    @FXML
    private Label price_label;

    @FXML
    private Label product_name_label;

    @FXML
    private Label quantity_label;

    @FXML
    private Label tag_label;

    @FXML
    private ImageView upload_img;


    public void init(int id) {

        inventoryCardDto product = ProductModel.getProductById(id);
        product_name_label.setText(product.getName());
        brand_label.setText(product.getBrand());
        description_label.setText(product.getDescription());
        price_label.setText(String.valueOf(product.getPrice()));
        quantity_label.setText(String.valueOf(product.getQuantity()));
        tag_label.setText(product.getTag());

        String absolutePath = new File(product.getImageUrl()).getAbsolutePath();
        Image image = new Image("file:" + absolutePath);

        if (product.getImageUrl().isEmpty()) {
            image = new Image(getClass().getResourceAsStream("/view/assets/icons/no_pic.png"));
        }

        upload_img.setImage(image);

    }
}
