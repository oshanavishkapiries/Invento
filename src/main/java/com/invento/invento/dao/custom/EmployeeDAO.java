package com.invento.invento.dao.custom;

import com.invento.invento.entity.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employee employee) throws SQLException;
    
    void updateEmployee(Employee employee) throws SQLException;
    
    boolean deleteEmployee(int employeeID) throws SQLException;
    
    Employee getEmployeeById(int employeeID) throws SQLException;
    
    List<Employee> getAllEmployees() throws SQLException;
    
    List<Employee> getAllEmployeesByRoleName(String roleName) throws SQLException;
    
    List<Employee> getAllEmployeesByDepartment(String departmentName) throws SQLException;
    
    List<Employee> getAllEmployeesByName(String name) throws SQLException;
    
    Employee getEmployeeByEmail(String email) throws SQLException;
} 