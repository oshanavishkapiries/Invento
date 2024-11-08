package com.invento.invento.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), gridView);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                com_borderpane.setCenter(gridView);
                fadeTransition.play();
            });

            listButton.setOnAction(event -> {
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), listViewContent);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                com_borderpane.setCenter(listViewContent);
                fadeTransition.play();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}