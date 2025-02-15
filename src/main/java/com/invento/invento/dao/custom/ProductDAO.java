package com.invento.invento.dao.custom;

import com.invento.invento.entity.InventoryCard;
import java.util.List;

public interface ProductDAO {
    List<InventoryCard> getAllProducts();
    
    boolean createProduct(InventoryCard product);
    
    List<String> getUniqueCategories();
    
    List<String> getUniqueBrands();
    
    InventoryCard getProductById(int productId);
    
    boolean updateProduct(InventoryCard product);
    
    boolean deleteProductById(int productId);
    
    List<InventoryCard> searchProductByName(String name);
} 