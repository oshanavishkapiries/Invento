package com.invento.invento.model;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    public static List<inventoryCardDto> getAllProducts() {
        String sql = "SELECT * FROM Product ORDER BY ProductID DESC";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {

            List<inventoryCardDto> products = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                String category = resultSet.getString("Category");
                String description = resultSet.getString("Description");
                String brand = resultSet.getString("Brand");
                double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("QuantityInStock");

                inventoryCardDto product = new inventoryCardDto(id, "image-url", description, name, brand, category, price, quantity);
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static boolean createProduct(inventoryCardDto product) {
        String sql = "INSERT INTO Product (Name, Category, Description, Brand, Price, QuantityInStock) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return CrudUtil.execute(sql,
                    product.getName(),
                    product.getTag(),
                    product.getDescription(),
                    product.getBrand(),
                    product.getPrice(),
                    product.getQuantity()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getUniqueCategories() {
        String sql = "SELECT DISTINCT Category FROM Product";
        List<String> categories = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            while (resultSet.next()) {
                categories.add(resultSet.getString("Category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static List<String> getUniqueBrands() {
        String sql = "SELECT DISTINCT Brand FROM Product";
        List<String> brands = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            while (resultSet.next()) {
                brands.add(resultSet.getString("Brand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    public static boolean deleteProductById(int productId) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try {
            return CrudUtil.execute(sql, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
