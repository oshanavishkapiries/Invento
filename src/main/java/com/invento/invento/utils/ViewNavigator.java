package com.invento.invento.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewNavigator {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        ViewNavigator.mainStage = stage;
    }

    public static void loadView(String fxmlPath, boolean isMaximized) {

        String path = "/view/" + fxmlPath + ".fxml";

        try {
            FXMLLoader loader = new FXMLLoader(ViewNavigator.class.getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setTitle("Invento");
            mainStage.setMaximized(isMaximized);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


