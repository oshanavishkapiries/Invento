package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.ItemCardData;
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
        List<ItemCardData> cardDataList = getCardData();
        try {
            int row = 0;
            int col = 0;
            for (ItemCardData cardData : cardDataList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/CardComponentList.fxml"));
                AnchorPane card = loader.load();

                CardComponent cardController = loader.getController();
                cardController.setData(cardData);

                gridPane.add(card, col, row);


                    row++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ItemCardData> getCardData() {

        return List.of(
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 1", "Description 1", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 2", "Description 2", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 3", "Description 3", "..\\..\\assets\\pic1.png"),
                new ItemCardData("Product 4", "Description 4", "..\\..\\assets\\pic1.png")
        );
    }
}