package com.invento.invento.controller.layout;

import com.invento.invento.utils.aminations.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dashLayout implements Initializable {

    @FXML
    private BorderPane border_pane;

    @FXML
    private VBox com_sider;

    private Button currentlySelectedButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadView("/view/pages/InventoryPage.fxml");

        ArrayList<btnData> nb = new ArrayList<>();

        nb.add(new btnData("/view/pages/InventoryPage.fxml", "Inventory"));
        nb.add(new btnData("/view/pages/OrderPage.fxml", "Order"));
        nb.add(new btnData("/view/pages/EmployeePage.fxml", "Employee"));

        for (btnData buttonData : nb) {
            addButtonToSidebar(buttonData);
        }
    }

    private void addButtonToSidebar(btnData buttonData) {
        Button button = new Button(buttonData.buttonText);

        String imagePath = "/view/assets/icons/" + buttonData.buttonText + ".png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);

        button.setGraphic(imageView);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setGraphicTextGap(10);
        button.setPadding(new Insets(10));

        button.setOnAction(event -> {
            loadLoader();
            loadViewInBackground(buttonData.pagePath);

            if (currentlySelectedButton != null) {
                currentlySelectedButton.getStyleClass().remove("clicked");
            }
            button.getStyleClass().add("clicked");
            currentlySelectedButton = button;
        });

        button.getStyleClass().add("navigation-button");
        com_sider.getChildren().add(button);
    }

    private void loadViewInBackground(String fxmlPath) {
        new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane view = loader.load();

                javafx.application.Platform.runLater(() -> {
                    border_pane.setCenter(view);
                    new FadeIn(view, 300);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void loadLoader() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/Loader.fxml"));
            AnchorPane loaderView = loader.load();
            border_pane.setCenter(loaderView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();
            border_pane.setCenter(view);
            new FadeIn(view, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class btnData {
        String pagePath;
        String buttonText;

        public btnData(String pagePath, String buttonText) {
            this.pagePath = pagePath;
            this.buttonText = buttonText;
        }
    }
}
