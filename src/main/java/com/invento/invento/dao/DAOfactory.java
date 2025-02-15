package com.invento.invento.dao;

import com.invento.invento.dao.custom.impl.*;

public class DAOfactory {
    private static DAOfactory daoFactory;

    private DAOfactory() {
    }

    public static DAOfactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOfactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, DEPARTMENT, EMPLOYEE, ORDER, PRODUCT, ROLE, SUPPLIER, ORDER_DETAIL
    }

    @SuppressWarnings("unchecked")
    public <T> T getDAO(DAOTypes type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDAOimpl();
            case DEPARTMENT:
                return (T) new DepartmentDAOimpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOimpl();
            case ORDER:
                return (T) new OrderDAOimpl();
            case PRODUCT:
                return (T) new ProductDAOimpl();
            case ROLE:
                return (T) new RoleDAOimpl();
            case SUPPLIER:
                return (T) new SupplierDAOimpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOimpl();    
            default:
                return null;
        }
    }
}
