package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class ListView {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        Reference.listView = this;

        try {
            if (!gridPane.getChildren().isEmpty()) {
                gridPane.getChildren().clear();
            }

            List<inventoryCardDto> cardDataList = ProductModel.getAllProducts();

            if (cardDataList.isEmpty()) {
                AlertUtil.showAlert("Information", "No Products Found", "No products are available to display in the list.");
            } else {
                includeInGridPane(cardDataList);
            }
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    public void includeInGridPane(List<inventoryCardDto> cardDataList) {
        int row = 0;

        for (inventoryCardDto cardData : cardDataList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/ListCard.fxml"));
                AnchorPane card = loader.load();

                BothCard cardController = loader.getController();
                cardController.setData(cardData);

                gridPane.add(card, 0, row);
                row++;
            } catch (IOException e) {
                AlertUtil.showErrorAlert("Error", "Card Loading Error", e.getMessage());
            }
        }
    }

    public void removeElement() {
        try {
            gridPane.getChildren().clear();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Clearing List Error", e.getMessage());
        }
    }
}
