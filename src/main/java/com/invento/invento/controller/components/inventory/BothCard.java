package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void setData(inventoryCardDto cardData) {
        name.setText(cardData.getName());
        brand.setText(cardData.getBrand());
        tag.setText(cardData.getTag());
        price.setText(String.valueOf(cardData.getPrice()));
        qty.setText(String.valueOf(cardData.getQuantity()));
        //image.setImage(new Image(cardData.getImageUrl()));
    }
}