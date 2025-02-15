package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.RoleDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.RoleService;
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

    private final RoleService roleService;

    public RoleController() {
        this.roleService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.ROLE);
    }

    @FXML
    private void initialize() {
        submit_btn.setOnAction(e -> onSubmitClick());
        name_input.setOnKeyTyped(e -> submit_btn.setDisable(name_input.getText().isEmpty()));
        search_input.setOnKeyTyped(e -> populate(roleService.searchRole(search_input.getText())));
        init();
    }

    private void init() {
        submit_btn.setDisable(true);
        populate(roleService.getAllRoles());
    }

    public void populate(List<RoleDto> roles) {
        listview.getChildren().clear();
        roles.forEach(role -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/employee/Role&DepartmentCard.fxml"));
                AnchorPane root = loader.load();
                RDCardController controller = loader.getController();
                controller.setData(role, this);
                listview.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void delete_item(int id) {
        if (roleService.deleteRole(id)) {
            AlertUtil.showSuccessAlert("Success", "Deleting Success", "Role has been deleted");
            init();
        } else {
            AlertUtil.showErrorAlert("Error", "Deleting Error", "Role cannot be deleted");
        }
    }

    public void onSubmitClick() {
        if (submit_btn.getText().equals("create")) {
            handleCreate();
        } else {
            handleUpdate();
        }
    }

    private void handleCreate() {
        if (roleService.createRole(name_input.getText(), dis_input.getText())) {
            AlertUtil.showSuccessAlert("Success", "Creation Success", "Role has been created");
            populate(roleService.getAllRoles());
            clear();
        } else {
            AlertUtil.showErrorAlert("Error", "Creation Error", "Role cannot be created");
        }
    }

    private void handleUpdate() {
        if (roleService.updateRole(temp_id, name_input.getText(), dis_input.getText())) {
            AlertUtil.showSuccessAlert("Success", "Update Success", "Role has been updated");
            populate(roleService.getAllRoles());
            clear();
        } else {
            AlertUtil.showErrorAlert("Error", "Update Error", "Role cannot be updated");
        }
    }

    private void clear() {
        name_input.clear();
        dis_input.clear();
        submit_btn.setText("create");
        temp_id = 0;
    }

    @Override
    public void edit_item(int id) {
        temp_id = id;
        RoleDto role = roleService.getRoleById(id);
        if (role != null) {
            name_input.setText(role.getRoleName());
            dis_input.setText(role.getDescription());
            submit_btn.setText("update");
        } else {
            AlertUtil.showErrorAlert("Error", "Fetching Error", "Role not found");
        }
    }
}
