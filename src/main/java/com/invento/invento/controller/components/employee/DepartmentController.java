package com.invento.invento.controller.components.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DepartmentController {

    @FXML
    private TextArea dis_input;

    @FXML
    private VBox listview;

    @FXML
    private TextField name_input;

    @FXML
    private TextField search_input;

    @FXML
    private Button submit_btn;

    @FXML
    private void initialize() {
        submit_btn.setOnAction(e -> {
            //todo save data
        });
        search_input.setOnKeyPressed(e -> {
            //todo search data
        });
    }

}
