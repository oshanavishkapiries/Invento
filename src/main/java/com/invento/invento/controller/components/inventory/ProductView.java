package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private final ProductService productService;

    public ProductView() {
        this.productService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.PRODUCT);
    }

    public void init(int id) {
        inventoryCardDto product = productService.getProductById(id);
        if (product != null) {
            product_name_label.setText(product.getName());
            brand_label.setText(product.getBrand());
            description_label.setText(product.getDescription());
            price_label.setText(String.valueOf(product.getPrice()));
            quantity_label.setText(String.valueOf(product.getQuantity()));
            tag_label.setText(product.getTag());
            setImage(product.getImageUrl());
        }
    }

    private void setImage(String path) {
        if (path == null || path.isEmpty()) {
            upload_img.setImage(new Image(getClass().getResourceAsStream("/view/assets/icons/no_pic.png")));
            return;
        }

        String absolutePath = new File(path).getAbsolutePath();
        upload_img.setImage(new Image("file:" + absolutePath));
    }
}
