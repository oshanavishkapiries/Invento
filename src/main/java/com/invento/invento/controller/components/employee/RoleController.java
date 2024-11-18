package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.RoleDto;
import com.invento.invento.model.RoleModel;
import com.invento.invento.utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class RoleController implements RDCard {

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

    private int temp_id;


    @FXML
    private void initialize() {
        init();
        name_input.setOnKeyTyped(e -> submit_btn.setDisable(name_input.getText().isEmpty()));
        submit_btn.setOnAction(e -> {
            onSubmitClick();
        });
        search_input.setOnKeyTyped(e -> {
            populate(RoleModel.searchRole(search_input.getText()));

        });
    }

    private void init() {
        submit_btn.setDisable(true);
        populate(RoleModel.getAllRoles());
    }

    public void populate(List<RoleDto> roles) {
        try {
            listview.getChildren().clear();
            for (RoleDto role : roles) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/employee/Role&DepartmentCard.fxml"));
                AnchorPane card = loader.load();
                RDCardController controller = loader.getController();
                controller.setData(role, this);
                listview.getChildren().add(card);
            }
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    public void delete_item(int id) {
        if (RoleModel.deleteRole(id)) {
            AlertUtil.showSuccessAlert("Success", "Deleting Success", "Role has been deleted");
            init();
        } else {
            AlertUtil.showErrorAlert("Error", "Deleting Error", "Role cannot be deleted");
        }
    }

    public void onSubmitClick() {
        if (submit_btn.getText().equals("create")) {
            if (RoleModel.createRole(name_input.getText(), dis_input.getText())) {
                AlertUtil.showSuccessAlert("Success", "Creation Success", "Role has been created");
                populate(RoleModel.getAllRoles());
                clear();
            } else {
                AlertUtil.showErrorAlert("Error", "Creation Error", "Role cannot be created");
            }
        } else {
            if (RoleModel.updateRole(temp_id, name_input.getText(), dis_input.getText())) {
                AlertUtil.showSuccessAlert("Success", "Update Success", "Role has been updated");
                populate(RoleModel.getAllRoles());
                clear();
            } else {
                AlertUtil.showErrorAlert("Error", "Update Error", "Role cannot be updated");
            }
        }
    }

    @Override
    public void edit_item(int id) {
        temp_id = id;
        RoleDto role = RoleModel.getRoleById(id);
        submit_btn.setText("update");
        System.out.println(role);
        if (role != null) {
            name_input.setText(role.getRoleName());
            dis_input.setText(role.getDescription());
        } else {
            AlertUtil.showErrorAlert("Error", "Fetching Error", "Role not found");
        }
    }

    public void clear() {
        name_input.clear();
        dis_input.clear();
        submit_btn.setText("create");
        submit_btn.setDisable(true);
    }
}
