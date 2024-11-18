package com.invento.invento.controller.components.inventory;

import com.invento.invento.controller.components.order.CustomerCard;
import com.invento.invento.dto.SupplierDto;
import com.invento.invento.model.SupplierModel;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class SupplierController {

    @FXML
    private VBox supplierListView;

    @FXML
    private Button supplier_done_button;

    @FXML
    private TextArea supplier_input_address;

    @FXML
    private TextField supplier_input_email;

    @FXML
    private TextField supplier_input_name;

    @FXML
    private TextField supplier_input_phone;

    private int temp_id;

    @FXML
    public void initialize() {
        Reference.SupplierController = this;
        populateSupplierListView();
        supplier_done_button.setDisable(true);
    }

    @FXML
    void supplier_input_name_onchange(KeyEvent event) {
        supplier_done_button.setDisable(supplier_input_name.getText().isEmpty());
    }

    public void supplier_update_data_set_load(int id) {
        SupplierDto supplier = SupplierModel.getSupplierById(id);
        this.temp_id = id;

        supplier_input_name.setText(supplier.getName());
        supplier_input_phone.setText(supplier.getPhone());
        supplier_input_email.setText(supplier.getEmail());
        supplier_input_address.setText(supplier.getAddress());
        supplier_done_button.setText("Update");
        supplier_done_button.setDisable(false);

    }

    @FXML
    void supplier_search_onchange(KeyEvent event) {
        TextField searchField = (TextField) event.getSource();
        String searchText = searchField.getText();

        try {
            supplierListView.getChildren().clear();
            List<SupplierDto> suppliers = SupplierModel.searchSuppliersByName(searchText);

            for (SupplierDto supplier : suppliers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/SupplierCard.fxml"));
                AnchorPane card = loader.load();
                SupplierCard controller = loader.getController();
                controller.setSupplier(supplier);
                supplierListView.getChildren().add(card);
            }
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    @FXML
    public void supplier_done_onclick(MouseEvent event) {

        String name = supplier_input_name.getText();
        String phone = supplier_input_phone.getText();
        String email = supplier_input_email.getText();
        String address = supplier_input_address.getText();

        if (supplier_done_button.getText().equals("Update")) {
            SupplierModel.updateSupplier(new SupplierDto(temp_id, name, phone, email, address));
            AlertUtil.showAlert("Success", "Supplier Updated", "Supplier updated successfully.");
            supplier_input_name.clear();
            supplier_input_phone.clear();
            supplier_input_email.clear();
            supplier_input_address.clear();

            supplier_done_button.setDisable(true);
            supplier_done_button.setText("Create");

            populateSupplierListView();
            return;
        }

        SupplierDto newSupplier = new SupplierDto(0, name, phone, email, address);

        if (SupplierModel.isEmailExist(email)) {
            AlertUtil.showErrorAlert("Error", "Email Error", "Email already exists.");
        } else if (SupplierModel.createSupplier(newSupplier)) {
            AlertUtil.showAlert("Success", "Supplier Added", "Supplier added successfully.");
            populateSupplierListView();
        } else {
            AlertUtil.showErrorAlert("Error", "Supplier Error", "Failed to add supplier.");
        }
    }

    @FXML
    public void populateSupplierListView() {
        try {
            supplierListView.getChildren().clear();
            List<SupplierDto> suppliers = SupplierModel.getAllSuppliers();

            for (SupplierDto supplier : suppliers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/SupplierCard.fxml"));
                AnchorPane card = loader.load();
                SupplierCard controller = loader.getController();
                controller.setSupplier(supplier);
                supplierListView.getChildren().add(card);
            }
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }
}
