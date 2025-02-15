package com.invento.invento.dao.custom;

import com.invento.invento.entity.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    boolean createCustomer(Customer customer) throws SQLException;
    
    List<Customer> getAllCustomers() throws SQLException;
    
    Customer getCustomerById(int customerId) throws SQLException;
    
    List<Customer> searchCustomersByName(String name) throws SQLException;
    
    boolean updateCustomer(Customer customer) throws SQLException;
    
    boolean deleteCustomer(int customerId) throws SQLException;
    
    boolean isEmailExist(String email) throws SQLException;
} 