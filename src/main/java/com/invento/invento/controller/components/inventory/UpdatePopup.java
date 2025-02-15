package com.invento.invento.controller.components.inventory;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.service.ServiceFactory;
import com.invento.invento.service.custom.ProductService;
import com.invento.invento.utils.AlertUtil;
import com.invento.invento.utils.Reference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UpdatePopup {

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

    private inventoryCardDto cardData;
    private final ProductService productService;

    public UpdatePopup() {
        this.productService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.PRODUCT);
    }

    public void initialize(int id) {
        try {
            cardData = productService.getProductById(id);
            loadCategories();
            loadBrands();
            initFields();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Failed to load product", e.getMessage());
        }
    }

    private List<String> tempCategories;
    private List<String> tempBrands;

    private void loadCategories() {
        tempCategories = productService.getUniqueCategories();
        ObservableList<String> categoryList = FXCollections.observableArrayList(tempCategories);
        product_category.setItems(categoryList);
    }

    private void loadBrands() {
        tempBrands = productService.getUniqueBrands();
        ObservableList<String> brandList = FXCollections.observableArrayList(tempBrands);
        product_brand.setItems(brandList);
    }

    private void initFields() {
        if (cardData != null) {
            product_name.setText(cardData.getName());
            product_description.setText(cardData.getDescription());
            product_price.setText(String.valueOf(cardData.getPrice()));
            product_quantityInStock.setText(String.valueOf(cardData.getQuantity()));
            product_brand.setValue(cardData.getBrand());
            product_category.setValue(cardData.getTag());
            setImage(cardData.getImageUrl());
        }
    }

    private void setImage(String path) {
        if (path == null || path.isEmpty()) {
            upload_img.setImage(new Image(getClass().getResourceAsStream("/view/assets/icons/no_pic.png")));
            return;
        }

        String absolutePath = new File(path).getAbsolutePath();
        upload_img.setImage(new Image("file:" + absolutePath));
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
            String returnImagePath = null;

            if (image != null && image.getUrl() != null) {
                String imagePathFromUrl = image.getUrl().replaceFirst("file:", "");
                File sourceFile = new File(imagePathFromUrl);

                if (sourceFile.exists()) {
                    String uniqueFileName = UUID.randomUUID().toString() + ".png";
                    String imagePath = folderPath + "/" + uniqueFileName;
                    Files.copy(sourceFile.toPath(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
                    returnImagePath = imagePath;
                }
            }
            return returnImagePath;
        } catch (IOException e) {
            AlertUtil.showErrorAlert("Error", "Image Saving Failed", e.getMessage());
            return null;
        }
    }

    @FXML
    void product_update_onclick(ActionEvent event) {
        try {
            inventoryCardDto updatedProduct = getUpdatedInventoryCardDto();

            if (productService.updateProduct(updatedProduct)) {
                new Alert(Alert.AlertType.INFORMATION, "Product Updated Successfully").showAndWait();
                Reference.InventoryController.init();
                Reference.UpdatePopupScene.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Update Failed").showAndWait();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid price or quantity").showAndWait();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Please select brand and category").showAndWait();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Product Update Error", e.getMessage());
        }
    }

    private inventoryCardDto getUpdatedInventoryCardDto() {
        String imgPath = save_img_click();
        String name = product_name.getText();
        String description = product_description.getText();
        String brand = product_brand.getValue();
        String category = product_category.getValue();
        double price = Double.parseDouble(product_price.getText());
        int quantity = Integer.parseInt(product_quantityInStock.getText());

        return new inventoryCardDto(cardData.getId(), imgPath, description, name, brand, category, price, quantity);
    }
}
