package com.invento.invento.dao.custom;

import com.invento.invento.entity.Order;
import com.invento.invento.entity.InventoryCard;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    boolean saveOrder(Order order, List<InventoryCard> items) throws SQLException;
    
    int getLastOrderId();
    
    boolean updateOrder(Order order);
    
    boolean deleteOrderById(int orderId);
    
    List<Order> searchOrderByDate(String orderDate);
} 