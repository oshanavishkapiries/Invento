package com.invento.invento.dao.custom;

import com.invento.invento.entity.Supplier;
import java.util.List;

public interface SupplierDAO {
    boolean createSupplier(Supplier supplier);
    
    List<Supplier> getAllSuppliers();
    
    Supplier getSupplierById(int supplierID);
    
    List<Supplier> searchSuppliersByName(String name);
    
    boolean updateSupplier(Supplier supplier);
    
    boolean deleteSupplier(int supplierID);
    
    boolean isEmailExist(String email);
} 