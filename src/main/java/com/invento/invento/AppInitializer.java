package com.invento.invento;

import com.invento.invento.utils.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewNavigator.setMainStage(primaryStage);
        ViewNavigator.loadView("layouts/dashLayout",true);
        //ViewNavigator.loadView("LoginPage",false);
    }
}
