package com.invento.invento.service.custom;

import com.invento.invento.dto.inventoryCardDto;
import java.util.List;

public interface ProductService {
    List<inventoryCardDto> getAllProducts();
    
    boolean createProduct(inventoryCardDto product);
    
    List<String> getUniqueCategories();
    
    List<String> getUniqueBrands();
    
    inventoryCardDto getProductById(int productId);
    
    boolean updateProduct(inventoryCardDto product);
    
    boolean deleteProductById(int productId);
    
    List<inventoryCardDto> searchProductByName(String name);
} 