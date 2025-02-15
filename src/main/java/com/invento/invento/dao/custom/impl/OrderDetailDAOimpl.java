package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.OrderDetailDAO;
import com.invento.invento.entity.InventoryCard;
import com.invento.invento.utils.CrudUtil;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOimpl implements OrderDetailDAO {
    
    @Override
    public boolean saveOrderDetails(int orderId, List<InventoryCard> items) throws SQLException {
        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, Quantity, Price) VALUES (?,?,?,?)";
        
        for (InventoryCard item : items) {
            boolean orderDetailSaved = CrudUtil.execute(sql, 
                orderId, 
                item.getId(), 
                item.getQuantity(), 
                (item.getPrice() * item.getQuantity())
            );
            if (!orderDetailSaved) {
                return false;
            }
        }
        return true;
    }
} 