package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.OrderDAO;
import com.invento.invento.entity.Order;
import com.invento.invento.entity.InventoryCard;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOimpl implements OrderDAO {
    

    @Override
    public boolean saveOrder(Order order, List<InventoryCard> items) throws SQLException {
        String sql = "INSERT INTO `Order` (CustomerID, OrderDate, TotalAmount) VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, 
            order.getCustomerID(), 
            order.getOrderDate(), 
            order.getTotalAmount()
        );
    }

    @Override
    public int getLastOrderId() {
        String sql = "SELECT OrderID AS LastOrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1";
        try {
            ResultSet resultSet = CrudUtil.execute(sql);
            if (resultSet.next()) {
                return resultSet.getInt("LastOrderID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        String sql = "UPDATE `Order` SET CustomerID = ?, OrderDate = ?, TotalAmount = ? WHERE OrderID = ?";
        try {
            return CrudUtil.execute(sql, 
                order.getCustomerID(), 
                order.getOrderDate(), 
                order.getTotalAmount(), 
                order.getOrderID()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrderById(int orderId) {
        String sql = "DELETE FROM `Order` WHERE OrderID = ?";
        try {
            return CrudUtil.execute(sql, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> searchOrderByDate(String orderDate) {
        String sql = "SELECT * FROM `Order` WHERE OrderDate = ?";
        List<Order> orders = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, orderDate)) {
            while (resultSet.next()) {
                orders.add(new Order(
                    resultSet.getInt("OrderID"),
                    resultSet.getInt("CustomerID"),
                    resultSet.getString("OrderDate"),
                    resultSet.getDouble("TotalAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}