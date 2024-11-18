package com.invento.invento.model;

import com.invento.invento.dto.SupplierDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static boolean createSupplier(SupplierDto supplier) {
        try {
            String sql = "INSERT INTO Supplier (Name, Phone, Email, Address) VALUES (?, ?, ?, ?)";
            return CrudUtil.execute(sql, supplier.getName(), supplier.getPhone(), supplier.getEmail(), supplier.getAddress());
        } catch (SQLException e) {
            System.err.println("Error while creating supplier: " + supplier.getName());
            e.printStackTrace();
            return false;
        }
    }

    public static List<SupplierDto> getAllSuppliers() {
        List<SupplierDto> supplierList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Supplier";
            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                supplierList.add(new SupplierDto(resultSet.getInt("SupplierID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address")));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving all suppliers.");
            e.printStackTrace();
        }
        return supplierList;
    }

    public static SupplierDto getSupplierById(int supplierID) {
        try {
            String sql = "SELECT * FROM Supplier WHERE SupplierID = ?";
            ResultSet resultSet = CrudUtil.execute(sql, supplierID);
            if (resultSet.next()) {
                return new SupplierDto(resultSet.getInt("SupplierID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address"));
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving supplier with ID: " + supplierID);
            e.printStackTrace();
        }
        return null;
    }

    public static List<SupplierDto> searchSuppliersByName(String name) {
        List<SupplierDto> supplierList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Supplier WHERE Name LIKE ?";
            ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%");
            while (resultSet.next()) {
                supplierList.add(new SupplierDto(resultSet.getInt("SupplierID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address")));
            }
        } catch (SQLException e) {
            System.err.println("Error while searching suppliers by name: " + name);
            e.printStackTrace();
        }
        return supplierList;
    }

    public static boolean updateSupplier(SupplierDto supplier) {
        try {
            String sql = "UPDATE Supplier SET Name = ?, Phone = ?, Email = ?, Address = ? WHERE SupplierID = ?";
            return CrudUtil.execute(sql, supplier.getName(), supplier.getPhone(), supplier.getEmail(), supplier.getAddress(), supplier.getSupplierID());
        } catch (SQLException e) {
            System.err.println("Error while updating supplier: " + supplier.getName());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSupplier(int supplierID) {
        try {
            String sql = "DELETE FROM Supplier WHERE SupplierID = ?";
            return CrudUtil.execute(sql, supplierID);
        } catch (SQLException e) {
            System.err.println("Error while deleting supplier with ID: " + supplierID);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isEmailExist(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM Supplier WHERE Email = ?";
            ResultSet resultSet = CrudUtil.execute(sql, email);
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error while checking email existence: " + email);
            e.printStackTrace();
            return false;
        }
    }


}
