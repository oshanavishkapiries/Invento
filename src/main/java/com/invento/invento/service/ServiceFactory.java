package com.invento.invento.service;

import com.invento.invento.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum ServiceTypes {
        CUSTOMER, DEPARTMENT, EMPLOYEE, ORDER, PRODUCT, ROLE, SUPPLIER
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(ServiceTypes type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case DEPARTMENT:
                return (T) new DepartmentServiceImpl();
            case EMPLOYEE:
                return (T) new EmployeeServiceImpl();
            case ORDER:
                return (T) new OrderServiceImpl();
            case PRODUCT:
                return (T) new ProductServiceImpl();
            case ROLE:
                return (T) new RoleServiceImpl();
            case SUPPLIER:
                return (T) new SupplierServiceImpl();
            default:
                return null;
        }
    }
}
