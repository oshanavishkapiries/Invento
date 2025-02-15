package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.EmployeeDAO;
import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.entity.Employee;
import com.invento.invento.service.custom.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeDAO employeeDAO;
    
    public EmployeeServiceImpl() {
        this.employeeDAO = DAOfactory.getInstance().getDAO(DAOTypes.EMPLOYEE);
    }
    
    @Override
    public void createEmployee(EmployeeDto employeeDto) throws SQLException {
        Employee employee = new Employee(
            employeeDto.getEmployeeID(),
            employeeDto.getRoleID(),
            employeeDto.getDepartmentID(),
            employeeDto.getName(),
            employeeDto.getAddress(),
            employeeDto.getPhone(),
            employeeDto.getEmail(),
            employeeDto.getPassword(),
            employeeDto.getPosition(),
            employeeDto.getSalary(),
            employeeDto.getRoleName(),
            employeeDto.getDepartmentName()
        );
        employeeDAO.createEmployee(employee);
    }
    
    @Override
    public void updateEmployee(EmployeeDto employeeDto) throws SQLException {
        Employee employee = new Employee(
            employeeDto.getEmployeeID(),
            employeeDto.getRoleID(),
            employeeDto.getDepartmentID(),
            employeeDto.getName(),
            employeeDto.getAddress(),
            employeeDto.getPhone(),
            employeeDto.getEmail(),
            employeeDto.getPassword(),
            employeeDto.getPosition(),
            employeeDto.getSalary(),
            employeeDto.getRoleName(),
            employeeDto.getDepartmentName()
        );
        employeeDAO.updateEmployee(employee);
    }
    
    @Override
    public boolean deleteEmployee(int employeeID) throws SQLException {
        return employeeDAO.deleteEmployee(employeeID);
    }
    
    @Override
    public EmployeeDto getEmployeeById(int employeeID) throws SQLException {
        Employee employee = employeeDAO.getEmployeeById(employeeID);
        if (employee != null) {
            return new EmployeeDto(
                employee.getEmployeeID(),
                employee.getRoleID(),
                employee.getDepartmentID(),
                employee.getName(),
                employee.getAddress(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getRoleName(),
                employee.getDepartmentName()
            );
        }
        return null;
    }
    
    private List<EmployeeDto> mapEmployeeList(List<Employee> employees) {
        return employees.stream()
            .map(employee -> new EmployeeDto(
                employee.getEmployeeID(),
                employee.getRoleID(),
                employee.getDepartmentID(),
                employee.getName(),
                employee.getAddress(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getRoleName(),
                employee.getDepartmentName()
            ))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<EmployeeDto> getAllEmployees() throws SQLException {
        return mapEmployeeList(employeeDAO.getAllEmployees());
    }
    
    @Override
    public List<EmployeeDto> getAllEmployeesByRoleName(String roleName) throws SQLException {
        return mapEmployeeList(employeeDAO.getAllEmployeesByRoleName(roleName));
    }
    
    @Override
    public List<EmployeeDto> getAllEmployeesByDepartment(String departmentName) throws SQLException {
        return mapEmployeeList(employeeDAO.getAllEmployeesByDepartment(departmentName));
    }
    
    @Override
    public List<EmployeeDto> getAllEmployeesByName(String name) throws SQLException {
        return mapEmployeeList(employeeDAO.getAllEmployeesByName(name));
    }
    
    @Override
    public EmployeeDto getEmployeeByEmail(String email) throws SQLException {
        Employee employee = employeeDAO.getEmployeeByEmail(email);
        if (employee != null) {
            return new EmployeeDto(
                employee.getEmployeeID(),
                employee.getEmail(),
                employee.getPassword()
            );
        }
        return null;
    }
} 