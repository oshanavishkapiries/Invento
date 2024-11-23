package com.invento.invento.model;

import com.invento.invento.dto.OrderDto;
import com.invento.invento.dto.inventoryCardDto;
import com.invento.invento.utils.CrudUtil;
import com.invento.invento.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    public static boolean saveOrder(OrderDto order, List<inventoryCardDto> items) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `Order` ( CustomerID, OrderDate, TotalAmount) VALUES ( ?, ?, ?)";
            boolean orderSaved = CrudUtil.execute(sql, 1, order.getOrderDate(), order.getTotalAmount());
            if (orderSaved) {
                int orderID = getLastOrderId();
                String orderDetailSql = "INSERT INTO OrderDetail (OrderID, ProductID, Quantity, Price) VALUES (?,?,?,?)";
                for (inventoryCardDto item : items) {
                    boolean orderDetailSaved = CrudUtil.execute(orderDetailSql, orderID, item.getId(), item.getQuantity(), (item.getPrice() * item.getQuantity()));
                    if (!orderDetailSaved) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
            System.out.println("Error saving order: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error setting auto commit true: " + e.getMessage());
            }
        }
    }


    public static int getLastOrderId() {
        String sql = "SELECT OrderID AS LastOrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1";
        try {
            ResultSet resultSet = CrudUtil.execute(sql);
            if (resultSet.next()) {
                return resultSet.getInt("LastOrderID");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last order ID: " + e.getMessage());
        }
        return 0;
    }

    public static boolean updateOrder(OrderDto order) {
        String sql = "UPDATE `Order` SET CustomerID = ?, OrderDate = ?, TotalAmount = ? WHERE OrderID = ?";
        try {
            return CrudUtil.execute(sql, order.getCustomerID(), order.getOrderDate(), order.getTotalAmount(), order.getOrderID());
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteOrderById(int orderId) {
        String sql = "DELETE FROM `Order` WHERE OrderID = ?";
        try {
            return CrudUtil.execute(sql, orderId);
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
            return false;
        }
    }

    public static List<OrderDto> searchOrderByDate(String orderDate) {
        String sql = "SELECT * FROM `Order` WHERE OrderDate = ?";
        List<OrderDto> orders = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, orderDate)) {
            while (resultSet.next()) {
                orders.add(new OrderDto(resultSet.getInt("OrderID"), resultSet.getInt("CustomerID"), resultSet.getString("OrderDate"), resultSet.getDouble("TotalAmount")));
            }
        } catch (SQLException e) {
            System.out.println("Error searching orders by date: " + e.getMessage());
        }
        return orders;
    }
}