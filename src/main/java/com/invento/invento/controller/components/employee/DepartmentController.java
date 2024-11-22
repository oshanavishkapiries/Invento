package com.invento.invento.controller.components.employee;

import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.dto.RoleDto;
import com.invento.invento.model.DepartmentModel;
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

public class DepartmentController implements RDCard {

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
        submit_btn.setOnAction(e -> onSubmitClick());
        name_input.setOnKeyTyped(e -> submit_btn.setDisable(name_input.getText().isEmpty()));
        search_input.setOnKeyTyped(e -> populate(DepartmentModel.searchDepartment(search_input.getText())));
        init();
    }

    private void init() {
        submit_btn.setDisable(true);
        populate(DepartmentModel.getAllDepartments());
    }

    private void onSubmitClick() {
        if (submit_btn.getText().equals("create")) {
            if (DepartmentModel.createDepartment(name_input.getText(), dis_input.getText())) {
                AlertUtil.showSuccessAlert("Success", "Creation Success", "Department has been created");
                populate(DepartmentModel.getAllDepartments());
                clear();
            } else {
                AlertUtil.showErrorAlert("Error", "Creation Error", "Department cannot be created");
            }
        } else {
            if (DepartmentModel.updateDepartment(temp_id, name_input.getText(), dis_input.getText())) {
                AlertUtil.showSuccessAlert("Success", "Update Success", "Department has been updated");
                populate(DepartmentModel.getAllDepartments());
                clear();
            } else {
                AlertUtil.showErrorAlert("Error", "Update Error", "Department cannot be updated");
            }
        }
    }

    private void populate(List<DepartmentDto> departments) {
        listview.getChildren().clear();
        departments.forEach(department -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/employee/Role&DepartmentCard.fxml"));
                AnchorPane root = loader.load();
                RDCardController controller = loader.getController();
                //  departmentdto into role dto
                RoleDto departmentdto = new RoleDto(department.getDepartmentID(), department.getName(), department.getLocation());
                controller.setData(departmentdto, this);
                listview.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void clear() {
        name_input.clear();
        dis_input.clear();
        submit_btn.setText("create");
        temp_id = 0;
    }

    public void edit_item(int id) {
        temp_id = id;
        DepartmentDto department = DepartmentModel.getDepartmentById(id);
        name_input.setText(department.getName());
        dis_input.setText(department.getLocation());
        submit_btn.setText("update");
    }

    public void delete_item(int id) {
        if (DepartmentModel.deleteDepartment(id)) {
            AlertUtil.showSuccessAlert("Success", "Deletion Success", "Department has been deleted");
            populate(DepartmentModel.getAllDepartments());
        } else {
            AlertUtil.showErrorAlert("Error", "Deletion Error", "Department cannot be deleted");
        }
    }
}