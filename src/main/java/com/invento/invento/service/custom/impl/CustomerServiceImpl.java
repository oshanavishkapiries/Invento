package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.CustomerDAO;
import com.invento.invento.dto.CustomerDto;
import com.invento.invento.entity.Customer;
import com.invento.invento.service.custom.CustomerService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerDAO customerDAO;
    
    public CustomerServiceImpl() {
        this.customerDAO = DAOfactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    }
    
    @Override
    public boolean createCustomer(CustomerDto customerDto) throws SQLException {
        Customer customer = new Customer(
            customerDto.getCustomerID(),
            customerDto.getName(),
            customerDto.getPhone(),
            customerDto.getEmail(),
            customerDto.getAddress()
        );
        return customerDAO.createCustomer(customer);
    }
    
    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers().stream()
            .map(customer -> new CustomerDto(
                customer.getCustomerID(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
            ))
            .collect(Collectors.toList());
    }
    
    @Override
    public CustomerDto getCustomerById(int customerId) throws SQLException {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            return new CustomerDto(
                customer.getCustomerID(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
            );
        }
        return null;
    }
    
    @Override
    public List<CustomerDto> searchCustomersByName(String name) throws SQLException {
        return customerDAO.searchCustomersByName(name).stream()
            .map(customer -> new CustomerDto(
                customer.getCustomerID(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
            ))
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        Customer customer = new Customer(
            customerDto.getCustomerID(),
            customerDto.getName(),
            customerDto.getPhone(),
            customerDto.getEmail(),
            customerDto.getAddress()
        );
        return customerDAO.updateCustomer(customer);
    }
    
    @Override
    public boolean deleteCustomer(int customerId) throws SQLException {
        return customerDAO.deleteCustomer(customerId);
    }
    
    @Override
    public boolean isEmailExist(String email) throws SQLException {
        return customerDAO.isEmailExist(email);
    }
} 