package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.SupplierDAO;
import com.invento.invento.dto.SupplierDto;
import com.invento.invento.entity.Supplier;
import com.invento.invento.service.custom.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    
    private final SupplierDAO supplierDAO;
    
    public SupplierServiceImpl() {
        this.supplierDAO = DAOfactory.getInstance().getDAO(DAOTypes.SUPPLIER);
    }
    
    @Override
    public boolean createSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapToEntity(supplierDto);
        return supplierDAO.createSupplier(supplier);
    }
    
    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierDAO.getAllSuppliers().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public SupplierDto getSupplierById(int supplierID) {
        Supplier supplier = supplierDAO.getSupplierById(supplierID);
        return supplier != null ? mapToDto(supplier) : null;
    }
    
    @Override
    public List<SupplierDto> searchSuppliersByName(String name) {
        return supplierDAO.searchSuppliersByName(name).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapToEntity(supplierDto);
        return supplierDAO.updateSupplier(supplier);
    }
    
    @Override
    public boolean deleteSupplier(int supplierID) {
        return supplierDAO.deleteSupplier(supplierID);
    }
    
    @Override
    public boolean isEmailExist(String email) {
        return supplierDAO.isEmailExist(email);
    }
    
    private SupplierDto mapToDto(Supplier entity) {
        return new SupplierDto(
            entity.getSupplierID(),
            entity.getName(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getAddress()
        );
    }
    
    private Supplier mapToEntity(SupplierDto dto) {
        return new Supplier(
            dto.getSupplierID(),
            dto.getName(),
            dto.getPhone(),
            dto.getEmail(),
            dto.getAddress()
        );
    }
} 