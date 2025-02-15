package com.invento.invento.dao.custom;

import com.invento.invento.entity.InventoryCard;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean saveOrderDetails(int orderId, List<InventoryCard> items) throws SQLException;
} 