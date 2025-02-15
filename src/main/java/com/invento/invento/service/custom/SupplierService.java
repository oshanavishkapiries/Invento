package com.invento.invento.service.custom;

import com.invento.invento.dto.SupplierDto;
import java.util.List;

public interface SupplierService {
    boolean createSupplier(SupplierDto supplier);
    
    List<SupplierDto> getAllSuppliers();
    
    SupplierDto getSupplierById(int supplierID);
    
    List<SupplierDto> searchSuppliersByName(String name);
    
    boolean updateSupplier(SupplierDto supplier);
    
    boolean deleteSupplier(int supplierID);
    
    boolean isEmailExist(String email);
} 