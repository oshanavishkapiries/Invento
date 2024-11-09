package com.invento.invento.model;

import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    public static List<inventoryCardDto> getAllProducts() {
        String sql = "SELECT * FROM Product";

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
}