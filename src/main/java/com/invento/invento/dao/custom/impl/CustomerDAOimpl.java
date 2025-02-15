package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.CustomerDAO;
import com.invento.invento.entity.Customer;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO {

    @Override
    public boolean createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (Name, Phone, Email, Address) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress());
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM Customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new Customer(
                resultSet.getInt("CustomerID"),
                resultSet.getString("Name"),
                resultSet.getString("Phone"),
                resultSet.getString("Email"),
                resultSet.getString("Address")
            ));
        }
        return customerList;
    }

    @Override
    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, customerId);
        if (resultSet.next()) {
            return new Customer(
                resultSet.getInt("CustomerID"),
                resultSet.getString("Name"),
                resultSet.getString("Phone"),
                resultSet.getString("Email"),
                resultSet.getString("Address")
            );
        }
        return null;
    }

    @Override
    public List<Customer> searchCustomersByName(String name) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Name LIKE ?";
        ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%");
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new Customer(
                resultSet.getInt("CustomerID"),
                resultSet.getString("Name"),
                resultSet.getString("Phone"),
                resultSet.getString("Email"),
                resultSet.getString("Address")
            ));
        }
        return customerList;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";
        return CrudUtil.execute(sql, 
            customer.getName(), 
            customer.getPhone(), 
            customer.getEmail(), 
            customer.getAddress(), 
            customer.getCustomerID()
        );
    }

    @Override
    public boolean deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        return CrudUtil.execute(sql, customerId);
    }

    @Override
    public boolean isEmailExist(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Customer WHERE Email = ?";
        ResultSet resultSet = CrudUtil.execute(sql, email);
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }
}
