package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.ProductDAO;
import com.invento.invento.entity.InventoryCard;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOimpl implements ProductDAO {

    @Override
    public List<InventoryCard> getAllProducts() {
        String sql = "SELECT * FROM Product ORDER BY ProductID DESC";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            List<InventoryCard> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(new InventoryCard(
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductImgUrl"),
                    resultSet.getString("Description"),
                    resultSet.getString("Name"),
                    resultSet.getString("Brand"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("Price"),
                    resultSet.getInt("QuantityInStock")
                ));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean createProduct(InventoryCard product) {
        String sql = "INSERT INTO Product (Name, Category, Description, Brand, Price, QuantityInStock, ProductImgUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            return CrudUtil.execute(sql,
                product.getName(),
                product.getTag(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getUniqueCategories() {
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

    @Override
    public List<String> getUniqueBrands() {
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

    @Override
    public InventoryCard getProductById(int productId) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, productId)) {
            if (resultSet.next()) {
                return new InventoryCard(
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductImgUrl"),
                    resultSet.getString("Description"),
                    resultSet.getString("Name"),
                    resultSet.getString("Brand"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("Price"),
                    resultSet.getInt("QuantityInStock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateProduct(InventoryCard product) {
        String sql = "UPDATE Product SET Name = ?, Category = ?, Brand = ?, Description = ?, Price = ?, QuantityInStock = ?, ProductImgUrl = ? WHERE ProductID = ?";
        try {
            return CrudUtil.execute(sql,
                product.getName(),
                product.getTag(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProductById(int productId) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try {
            return CrudUtil.execute(sql, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<InventoryCard> searchProductByName(String name) {
        String sql = "SELECT * FROM Product WHERE Name LIKE ?";
        List<InventoryCard> products = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%")) {
            while (resultSet.next()) {
                products.add(new InventoryCard(
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductImgUrl"),
                    resultSet.getString("Description"),
                    resultSet.getString("Name"),
                    resultSet.getString("Brand"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("Price"),
                    resultSet.getInt("QuantityInStock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
