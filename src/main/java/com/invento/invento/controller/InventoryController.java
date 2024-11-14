package com.invento.invento.controller;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.model.ProductModel;
import com.invento.invento.utils.Reference;
import com.invento.invento.utils.aminations.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InventoryController {

    @FXML
    private BorderPane com_borderpane;

    @FXML
    private Button gridButton;

    @FXML
    private Button listButton;

    @FXML
    private ComboBox<String> product_brand;

    @FXML
    private ComboBox<String> product_category;

    @FXML
    private TextArea product_description;

    @FXML
    private TextField product_name;

    @FXML
    private TextField product_price;

    @FXML
    private TextField product_quantityInStock;

    @FXML
    private ImageView upload_img;

    @FXML
    public void initialize() {
        Reference.InventoryController = this;
        init();
        loadCategories();
        loadBrands();
    }

    private List<String> tempCategories = ProductModel.getUniqueCategories();
    private List<String> tempBrands = ProductModel.getUniqueBrands();

    private void loadCategories() {
        ObservableList<String> categoryList = FXCollections.observableArrayList(tempCategories);
        product_category.setItems(categoryList);
    }

    private void loadBrands() {
        ObservableList<String> brandList = FXCollections.observableArrayList(tempBrands);
        product_brand.setItems(brandList);
    }

    public void init() {
        try {
            AnchorPane gridView = FXMLLoader.load(getClass().getResource("/view/components/inventory/GridView.fxml"));
            AnchorPane listViewContent = FXMLLoader.load(getClass().getResource("/view/components/inventory/ListView.fxml"));
            com_borderpane.setCenter(gridView);

            gridButton.setOnAction(event -> {
                com_borderpane.setCenter(gridView);
                new FadeIn(gridView, 300);
            });

            listButton.setOnAction(event -> {
                com_borderpane.setCenter(listViewContent);
                new FadeIn(listViewContent, 300);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void upload_img_click(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(upload_img.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            upload_img.setImage(image);
        }
    }

    String save_img_click() {
        try {
            String folderPath = "productImg";
            Files.createDirectories(Paths.get(folderPath));

            Image image = upload_img.getImage();
            String ReturnimagePath = null;
            if (image != null) {
                String uniqueFileName = UUID.randomUUID().toString() + ".png";
                String imagePath = folderPath + "/" + uniqueFileName;

                Files.copy(Paths.get(image.getUrl().substring(6)), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saved image path: " + imagePath);
                ReturnimagePath = imagePath;
            }
            return ReturnimagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    void clean_img_click() {
        Image image = new Image(getClass().getResourceAsStream("/view/assets/icons/addPic.png"));
        upload_img.setImage(image);
    }

    @FXML
    void product_category_add_onclick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Category");
        dialog.setHeaderText("Enter the name of the new category");
        dialog.setContentText("Category:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newCategory -> {
            if (!tempCategories.contains(newCategory)) {
                tempCategories.add(newCategory);
                loadCategories();
                new Alert(Alert.AlertType.INFORMATION, "Category added successfully!").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Category already exists!").showAndWait();
            }
        });
    }

    @FXML
    void product_brand_add_onclick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Brand");
        dialog.setHeaderText("Enter the name of the new brand");
        dialog.setContentText("Brand:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newBrand -> {
            if (!tempBrands.contains(newBrand)) {
                tempBrands.add(newBrand);
                loadBrands();
                new Alert(Alert.AlertType.INFORMATION, "Brand added successfully!").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Brand already exists!").showAndWait();
            }
        });
    }

    @FXML
    void product_submit_onclick(ActionEvent event) {
        try {
            inventoryCardDto product = getInventoryCardDto();


            if (ProductModel.createProduct(product)) {
                product_name.clear();
                product_description.clear();
                product_brand.setValue(null);
                product_category.setValue(null);
                product_price.clear();
                product_quantityInStock.clear();
                clean_img_click();

                new Alert(Alert.AlertType.INFORMATION, "Product Added Successfully").showAndWait();
                init();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Adding Failed").showAndWait();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid price or quantity").showAndWait();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Please select brand and category").showAndWait();
        }
    }

    private inventoryCardDto getInventoryCardDto() {
        String imgPath = save_img_click();
        String name = product_name.getText();
        String description = product_description.getText();
        String brand = product_brand.getValue();
        String category = product_category.getValue();
        double price = Double.parseDouble(product_price.getText());
        int quantity = Integer.parseInt(product_quantityInStock.getText());

        inventoryCardDto product = new inventoryCardDto(0, imgPath, description, name, brand, category, price, quantity);
        return product;
    }
}