package com.invento.invento.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InventoryController {

    @FXML
    private BorderPane com_borderpane;

    @FXML
    private Button gridButton;

    @FXML
    private Button listButton;

    @FXML
    public void initialize() {
        try {

            AnchorPane gridView = FXMLLoader.load(getClass().getResource("/view/components/inventory/GridView.fxml"));
            AnchorPane listViewContent = FXMLLoader.load(getClass().getResource("/view/components/inventory/ListView.fxml"));
            com_borderpane.setCenter(gridView);

            gridButton.setOnAction(event -> {
                com_borderpane.setCenter(gridView);
            });

            listButton.setOnAction(event -> {
                com_borderpane.setCenter(listViewContent);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}