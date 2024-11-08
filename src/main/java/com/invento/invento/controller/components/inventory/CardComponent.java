package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.ItemCardData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardComponent {

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    public void setData(ItemCardData cardData) {
        title.setText(cardData.getTitle());
//        image.setImage(new Image(cardData.getImageUrl()));
    }

}
