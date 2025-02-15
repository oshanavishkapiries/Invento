package com.invento.invento.service.custom;

import com.invento.invento.dto.EmployeeDto;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeDto employee) throws SQLException;
    
    void updateEmployee(EmployeeDto employee) throws SQLException;
    
    boolean deleteEmployee(int employeeID) throws SQLException;
    
    EmployeeDto getEmployeeById(int employeeID) throws SQLException;
    
    List<EmployeeDto> getAllEmployees() throws SQLException;
    
    List<EmployeeDto> getAllEmployeesByRoleName(String roleName) throws SQLException;
    
    List<EmployeeDto> getAllEmployeesByDepartment(String departmentName) throws SQLException;
    
    List<EmployeeDto> getAllEmployeesByName(String name) throws SQLException;
    
    EmployeeDto getEmployeeByEmail(String email) throws SQLException;
} 