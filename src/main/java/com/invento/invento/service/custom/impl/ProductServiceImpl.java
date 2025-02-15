package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.ProductDAO;
import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.entity.InventoryCard;
import com.invento.invento.service.custom.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    
    private final ProductDAO productDAO;
    
    public ProductServiceImpl() {
        this.productDAO = DAOfactory.getInstance().getDAO(DAOTypes.PRODUCT);
    }
    
    @Override
    public List<inventoryCardDto> getAllProducts() {
        return productDAO.getAllProducts().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean createProduct(inventoryCardDto productDto) {
        InventoryCard product = mapToEntity(productDto);
        return productDAO.createProduct(product);
    }
    
    @Override
    public List<String> getUniqueCategories() {
        return productDAO.getUniqueCategories();
    }
    
    @Override
    public List<String> getUniqueBrands() {
        return productDAO.getUniqueBrands();
    }
    
    @Override
    public inventoryCardDto getProductById(int productId) {
        InventoryCard product = productDAO.getProductById(productId);
        return product != null ? mapToDto(product) : null;
    }
    
    @Override
    public boolean updateProduct(inventoryCardDto productDto) {
        InventoryCard product = mapToEntity(productDto);
        return productDAO.updateProduct(product);
    }
    
    @Override
    public boolean deleteProductById(int productId) {
        return productDAO.deleteProductById(productId);
    }
    
    @Override
    public List<inventoryCardDto> searchProductByName(String name) {
        return productDAO.searchProductByName(name).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private inventoryCardDto mapToDto(InventoryCard entity) {
        return new inventoryCardDto(
            entity.getId(),
            entity.getImageUrl(),
            entity.getDescription(),
            entity.getName(),
            entity.getBrand(),
            entity.getTag(),
            entity.getPrice(),
            entity.getQuantity()
        );
    }
    
    private InventoryCard mapToEntity(inventoryCardDto dto) {
        return new InventoryCard(
            dto.getId(),
            dto.getImageUrl(),
            dto.getDescription(),
            dto.getName(),
            dto.getBrand(),
            dto.getTag(),
            dto.getPrice(),
            dto.getQuantity()
        );
    }
} 