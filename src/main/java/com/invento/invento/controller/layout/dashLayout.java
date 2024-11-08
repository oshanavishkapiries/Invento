package com.invento.invento.controller.layout;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dashLayout implements Initializable {

    @FXML
    private BorderPane border_pane;

    @FXML
    private VBox com_sider;

    private List<btnData> navigationButtons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadView("/view/pages/DashBoardPage.fxml");

        ArrayList<btnData> nb = new ArrayList<>();


        nb.add(new btnData(FontAwesomeIcon.TACHOMETER, "/view/pages/DashBoardPage.fxml", "Dashboard"));
        nb.add(new btnData(FontAwesomeIcon.HOME, "/view/pages/InventoryPage.fxml", "Inventory"));
        nb.add(new btnData(FontAwesomeIcon.SHOPPING_CART, "/view/pages/OrderPage.fxml", "Order"));
        nb.add(new btnData(FontAwesomeIcon.CREDIT_CARD, "/view/pages/PaymentPage.fxml", "Payment"));
        nb.add(new btnData(FontAwesomeIcon.USERS, "/view/pages/EmployeePage.fxml", "Employee"));


        for (btnData buttonData : nb) {
            addButtonToSidebar(buttonData);
        }
    }

    private void addButtonToSidebar(btnData buttonData) {
        Button button = new Button(buttonData.buttonText);
        FontAwesomeIconView iconView = new FontAwesomeIconView(buttonData.icon);
        iconView.setGlyphSize(16);
        button.setGraphic(iconView);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setGraphicTextGap(10);
        button.setPadding(new Insets(10));


        button.setOnAction(event -> loadView(buttonData.pagePath));
        button.getStyleClass().add("navigation-button");
        com_sider.getChildren().add(button);
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), view);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            border_pane.setCenter(view);
            fadeTransition.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class btnData {
        FontAwesomeIcon icon;
        String pagePath;
        String buttonText;

        public btnData(FontAwesomeIcon icon, String pagePath, String buttonText) {
            this.icon = icon;
            this.pagePath = pagePath;
            this.buttonText = buttonText;
        }
    }
}