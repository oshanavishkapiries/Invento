package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.ProductService;
import com.invento.invento.utils.AlertUtil;
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

    private final ProductService productService;

    public GridView() {
        this.productService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.PRODUCT);
    }

    @FXML
    public void initialize() {
        Reference.gridView = this;

        try {
            if (!gridPane.getChildren().isEmpty()) {
                gridPane.getChildren().clear();
            }

            List<inventoryCardDto> cardDataList = productService.getAllProducts();

            if (cardDataList.isEmpty()) {
                AlertUtil.showAlert("Information", "No Products Found", "No products are available to display.");
            } else {
                includeInGridPane(cardDataList);
            }
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Initialization Error", e.getMessage());
        }
    }

    public void includeInGridPane(List<inventoryCardDto> cardDataList) {
        int row = 0;
        int col = 0;

        for (inventoryCardDto cardData : cardDataList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/GridCard.fxml"));
                AnchorPane card = loader.load();

                BothCard cardController = loader.getController();
                cardController.setData(cardData);

                gridPane.add(card, col, row);

                col++;
                if (col > 4) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                AlertUtil.showErrorAlert("Error", "Card Loading Error", e.getMessage());
            }
        }
    }

    public void removeElement() {
        try {
            gridPane.getChildren().clear();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Clearing Grid Error", e.getMessage());
        }
    }
}
