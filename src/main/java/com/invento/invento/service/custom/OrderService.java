package com.invento.invento.service.custom;

import com.invento.invento.dto.OrderDto;
import com.invento.invento.dto.inventoryCardDto;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    boolean saveOrder(OrderDto order, List<inventoryCardDto> items) throws SQLException;
    
    int getLastOrderId();
    
    boolean updateOrder(OrderDto order);
    
    boolean deleteOrderById(int orderId);
    
    List<OrderDto> searchOrderByDate(String orderDate);
} 