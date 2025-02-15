package com.invento.invento.service.custom;

import com.invento.invento.dto.CustomerDto;
import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    boolean createCustomer(CustomerDto customer) throws SQLException;
    
    List<CustomerDto> getAllCustomers() throws SQLException;
    
    CustomerDto getCustomerById(int customerId) throws SQLException;
    
    List<CustomerDto> searchCustomersByName(String name) throws SQLException;
    
    boolean updateCustomer(CustomerDto customer) throws SQLException;
    
    boolean deleteCustomer(int customerId) throws SQLException;
    
    boolean isEmailExist(String email) throws SQLException;
} 