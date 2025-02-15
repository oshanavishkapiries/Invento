package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.SupplierDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.SupplierService;
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

    private final SupplierService supplierService;

    public SupplierController() {
        this.supplierService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.SUPPLIER);
    }

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
        SupplierDto supplier = supplierService.getSupplierById(id);
        if (supplier != null) {
            this.temp_id = id;
            supplier_input_name.setText(supplier.getName());
            supplier_input_phone.setText(supplier.getPhone());
            supplier_input_email.setText(supplier.getEmail());
            supplier_input_address.setText(supplier.getAddress());
            supplier_done_button.setText("Update");
            supplier_done_button.setDisable(false);
        }
    }

    @FXML
    void supplier_search_onchange(KeyEvent event) {
        TextField searchField = (TextField) event.getSource();
        String searchText = searchField.getText();

        try {
            supplierListView.getChildren().clear();
            List<SupplierDto> suppliers = supplierService.searchSuppliersByName(searchText);
            populateSupplierCards(suppliers);
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
            handleUpdate(name, phone, email, address);
        } else {
            handleCreate(name, phone, email, address);
        }
    }

    private void handleUpdate(String name, String phone, String email, String address) {
        SupplierDto supplierDto = new SupplierDto(temp_id, name, phone, email, address);
        if (supplierService.updateSupplier(supplierDto)) {
            AlertUtil.showAlert("Success", "Supplier Updated", "Supplier updated successfully.");
            clearForm();
            populateSupplierListView();
        } else {
            AlertUtil.showErrorAlert("Error", "Update Error", "Failed to update supplier.");
        }
    }

    private void handleCreate(String name, String phone, String email, String address) {
        if (supplierService.isEmailExist(email)) {
            AlertUtil.showErrorAlert("Error", "Email Error", "Email already exists.");
            return;
        }

        SupplierDto newSupplier = new SupplierDto(0, name, phone, email, address);
        if (supplierService.createSupplier(newSupplier)) {
            AlertUtil.showAlert("Success", "Supplier Added", "Supplier added successfully.");
            clearForm();
            populateSupplierListView();
        } else {
            AlertUtil.showErrorAlert("Error", "Supplier Error", "Failed to add supplier.");
        }
    }

    private void clearForm() {
        supplier_input_name.clear();
        supplier_input_phone.clear();
        supplier_input_email.clear();
        supplier_input_address.clear();
        supplier_done_button.setDisable(true);
        supplier_done_button.setText("Create");
        temp_id = 0;
    }

    @FXML
    public void populateSupplierListView() {
        try {
            supplierListView.getChildren().clear();
            List<SupplierDto> suppliers = supplierService.getAllSuppliers();
            populateSupplierCards(suppliers);
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Loading Error", e.getMessage());
        }
    }

    private void populateSupplierCards(List<SupplierDto> suppliers) throws IOException {
        for (SupplierDto supplier : suppliers) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/inventory/SupplierCard.fxml"));
            AnchorPane card = loader.load();
            SupplierCard controller = loader.getController();
            controller.setSupplier(supplier);
            supplierListView.getChildren().add(card);
        }
    }
}
