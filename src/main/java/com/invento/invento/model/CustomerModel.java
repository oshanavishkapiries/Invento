package com.invento.invento.model;

import com.invento.invento.dto.CustomerDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static boolean createCustomer(CustomerDto customer) throws SQLException {
        String sql = "INSERT INTO Customer (Name, Phone, Email, Address) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress());
    }

    public static List<CustomerDto> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM Customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<CustomerDto> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new CustomerDto(resultSet.getInt("CustomerID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address")));
        }
        return customerList;
    }

    public static CustomerDto getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, customerId);
        if (resultSet.next()) {
            return new CustomerDto(resultSet.getInt("CustomerID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address"));
        }
        return null;
    }

    public static List<CustomerDto> searchCustomersByName(String name) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Name LIKE ?";
        ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%");
        List<CustomerDto> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new CustomerDto(resultSet.getInt("CustomerID"), resultSet.getString("Name"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Address")));
        }
        return customerList;
    }

    public static boolean updateCustomer(CustomerDto customer) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";
        return CrudUtil.execute(sql, customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress(), customer.getCustomerID());

    }

    public static boolean deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        return CrudUtil.execute(sql, customerId);
    }

    public static boolean isEmailExist(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Customer WHERE Email = ?";
        ResultSet resultSet = CrudUtil.execute(sql, email);
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }
}
