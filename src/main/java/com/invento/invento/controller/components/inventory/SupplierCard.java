package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.SupplierDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.SupplierService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class SupplierCard {
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Label email;

    @FXML
    private Label name_;

    @FXML
    private Label phone;

    @FXML
    private Label address;

    private int Id;

    private final SupplierService supplierService;

    public SupplierCard() {
        this.supplierService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.SUPPLIER);
    }

    @FXML
    private void initialize() {
        btn_edit.setOnAction(e -> onEditClick());
        btn_delete.setOnAction(e -> onDeleteClick());
    }

    private void onEditClick() {
        Reference.SupplierController.supplier_update_data_set_load(Id);
    }

    private void onDeleteClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
            "Are you sure you want to delete this Supplier?", 
            ButtonType.YES, ButtonType.NO);
            
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                deleteSupplier();
            }
        });
    }

    private void deleteSupplier() {
        if (supplierService.deleteSupplier(Id)) {
            AlertUtil.showAlert("Success", "Supplier Deleted", "Supplier deleted successfully.");
            Reference.SupplierController.populateSupplierListView();
        } else {
            AlertUtil.showErrorAlert("Error", "Deletion Error", "Failed to delete Supplier.");
        }
    }

    public void setSupplier(SupplierDto supplier) {
        this.Id = supplier.getSupplierID();
        this.email.setText(supplier.getEmail());
        this.name_.setText(supplier.getName());
        this.phone.setText(supplier.getPhone());
        this.address.setText(supplier.getAddress());
    }
}
