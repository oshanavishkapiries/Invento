package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.RoleDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RDCardController {

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Label dis;

    @FXML
    private Label title;

    private RDCard controller;

    private int id;


    @FXML
    private void initialize() {

        btn_edit.setOnAction(event -> {
            controller.edit_item(id);
        });
        btn_delete.setOnAction(event -> {
            controller.delete_item(id);
        });
    }


    public void setData(RoleDto data, RDCard controller) {
        this.controller = controller;
        this.id = data.getRoleId();
        title.setText(data.getRoleName());
        dis.setText(data.getDescription());
    }


}
