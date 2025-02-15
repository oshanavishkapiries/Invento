package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.SupplierDAO;
import com.invento.invento.entity.Supplier;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOimpl implements SupplierDAO {

    @Override
    public boolean createSupplier(Supplier supplier) {
        try {
            String sql = "INSERT INTO Supplier (Name, Phone, Email, Address) VALUES (?, ?, ?, ?)";
            return CrudUtil.execute(sql, 
                supplier.getName(), 
                supplier.getPhone(), 
                supplier.getEmail(), 
                supplier.getAddress()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Supplier";
            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                supplierList.add(new Supplier(
                    resultSet.getInt("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public Supplier getSupplierById(int supplierID) {
        try {
            String sql = "SELECT * FROM Supplier WHERE SupplierID = ?";
            ResultSet resultSet = CrudUtil.execute(sql, supplierID);
            if (resultSet.next()) {
                return new Supplier(
                    resultSet.getInt("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Supplier> searchSuppliersByName(String name) {
        List<Supplier> supplierList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Supplier WHERE Name LIKE ?";
            ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%");
            while (resultSet.next()) {
                supplierList.add(new Supplier(
                    resultSet.getInt("SupplierID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        try {
            String sql = "UPDATE Supplier SET Name = ?, Phone = ?, Email = ?, Address = ? WHERE SupplierID = ?";
            return CrudUtil.execute(sql,
                supplier.getName(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getSupplierID()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(int supplierID) {
        try {
            String sql = "DELETE FROM Supplier WHERE SupplierID = ?";
            return CrudUtil.execute(sql, supplierID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM Supplier WHERE Email = ?";
            ResultSet resultSet = CrudUtil.execute(sql, email);
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
