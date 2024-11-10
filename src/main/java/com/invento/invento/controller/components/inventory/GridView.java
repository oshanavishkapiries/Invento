package com.invento.invento.controller.components.inventory;


import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class GridView {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {


        Reference.gridView = this;

        if (!gridPane.getChildren().isEmpty()) {
            gridPane.getChildren().clear();
        }
        List<inventoryCardDto> cardDataList = ProductModel.getAllProducts();
        try {
            int row = 0;
            int col = 0;
            for (inventoryCardDto cardData : cardDataList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/GridCard.fxml"));
                AnchorPane card = loader.load();

                BothCard cardController = loader.getController();
                cardController.setData(cardData);


                gridPane.add(card, col, row);

                col++;
                if (col > 3) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}