package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.OrderDAO;
import com.invento.invento.dao.custom.OrderDetailDAO;
import com.invento.invento.dto.OrderDto;
import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.entity.Order;
import com.invento.invento.service.custom.OrderService;
import com.invento.invento.entity.InventoryCard;
import com.invento.invento.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;
    
    public OrderServiceImpl() {
        this.orderDAO = DAOfactory.getInstance().getDAO(DAOTypes.ORDER);
        this.orderDetailDAO = DAOfactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    }
    
    @Override
    public boolean saveOrder(OrderDto orderDto, List<inventoryCardDto> itemDtos) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            
            Order order = new Order(
                orderDto.getOrderID(),
                orderDto.getCustomerID(),
                orderDto.getOrderDate(),
                orderDto.getTotalAmount()
            );
            
            List<InventoryCard> items = itemDtos.stream()
                .map(dto -> new InventoryCard(
                    dto.getId(),
                    dto.getImageUrl(),
                    dto.getDescription(),
                    dto.getName(),
                    dto.getBrand(),
                    dto.getTag(),
                    dto.getPrice(),
                    dto.getQuantity()
                ))
                .collect(Collectors.toList());
            
            boolean orderSaved = orderDAO.saveOrder(order, items);
            
            if (orderSaved) {
                int orderId = orderDAO.getLastOrderId();
                boolean detailsSaved = orderDetailDAO.saveOrderDetails(orderId, items);
                
                if (detailsSaved) {
                    connection.commit();
                    return true;
                }
            }
            
            connection.rollback();
            return false;
            
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    
    @Override
    public int getLastOrderId() {
        return orderDAO.getLastOrderId();
    }
    
    @Override
    public boolean updateOrder(OrderDto orderDto) {
        Order order = new Order(
            orderDto.getOrderID(),
            orderDto.getCustomerID(),
            orderDto.getOrderDate(),
            orderDto.getTotalAmount()
        );
        return orderDAO.updateOrder(order);
    }
    
    @Override
    public boolean deleteOrderById(int orderId) {
        return orderDAO.deleteOrderById(orderId);
    }
    
    @Override
    public List<OrderDto> searchOrderByDate(String orderDate) {
        return orderDAO.searchOrderByDate(orderDate).stream()
            .map(order -> new OrderDto(
                order.getOrderID(),
                order.getCustomerID(),
                order.getOrderDate(),
                order.getTotalAmount()
            ))
            .collect(Collectors.toList());
    }
} 