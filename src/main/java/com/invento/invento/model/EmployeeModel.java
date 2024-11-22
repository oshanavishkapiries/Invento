package com.invento.invento.model;

import com.invento.invento.dto.EmployeeDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public static void createEmployee(EmployeeDto employee) throws SQLException {
        String sql = "INSERT INTO Employee (RoleID, DepartmentID, Name, Address, Phone, Email, Password, Position, Salary) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CrudUtil.execute(sql, employee.getRoleID(), employee.getDepartmentID(), employee.getName(), employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getPassword(), employee.getPosition(), employee.getSalary());
    }

    public static void updateEmployee(EmployeeDto employee) throws SQLException {
        String sql = "UPDATE Employee SET RoleID = ?, DepartmentID = ?, Name = ?, Address = ?, Phone = ?, Email = ?, Password = ?, Position = ?, Salary = ? WHERE EmployeeID = ?";
        CrudUtil.execute(sql, employee.getRoleID(), employee.getDepartmentID(), employee.getName(), employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getPassword(), employee.getPosition(), employee.getSalary(), employee.getEmployeeID());
    }

    public static boolean deleteEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
        return CrudUtil.execute(sql, employeeID);
    }

    public static EmployeeDto getEmployeeById(int employeeID) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName " +
                "FROM Employee e " +
                "INNER JOIN Role r ON e.RoleID = r.RoleID " +
                "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID " +
                "WHERE e.EmployeeID = ?";
        try (ResultSet resultSet = CrudUtil.execute(sql, employeeID)) {
            if (resultSet.next()) {
                return new EmployeeDto(resultSet.getInt("EmployeeID"), resultSet.getInt("RoleID"), resultSet.getInt("DepartmentID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Position"), resultSet.getDouble("Salary"),resultSet.getString("RoleName"), resultSet.getString("DepartmentName"));
            }
        }
        return null;
    }

    public static List<EmployeeDto> getAllEmployees() throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName " +
                "FROM Employee e " +
                "INNER JOIN Role r ON e.RoleID = r.RoleID " +
                "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID";
        List<EmployeeDto> EmployeeDtos = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            while (resultSet.next()) {
                EmployeeDtos.add(new EmployeeDto(resultSet.getInt("EmployeeID"), resultSet.getInt("RoleID"), resultSet.getInt("DepartmentID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Position"), resultSet.getDouble("Salary"), resultSet.getString("RoleName"), resultSet.getString("DepartmentName")));
            }
        }
        return EmployeeDtos;
    }

    public static List<EmployeeDto> getAllEmployeesByRoleName(String roleName) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName " +
                "FROM Employee e " +
                "INNER JOIN Role r ON e.RoleID = r.RoleID " +
                "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID " +
                "WHERE r.RoleName = ?";
        List<EmployeeDto> EmployeeDtos = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, roleName)) {
            while (resultSet.next()) {
                EmployeeDtos.add(new EmployeeDto(resultSet.getInt("EmployeeID"), resultSet.getInt("RoleID"), resultSet.getInt("DepartmentID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Position"), resultSet.getDouble("Salary"), resultSet.getString("RoleName"), resultSet.getString("DepartmentName")));
            }
        }
        return EmployeeDtos;
    }

    public static List<EmployeeDto> getAllEmployeesByDepartment(String departmentName) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName " +
                "FROM Employee e " +
                "INNER JOIN Role r ON e.RoleID = r.RoleID " +
                "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID " +
                "WHERE d.Name = ?";
        List<EmployeeDto> EmployeeDtos = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, departmentName)) {
            while (resultSet.next()) {
                EmployeeDtos.add(new EmployeeDto(resultSet.getInt("EmployeeID"), resultSet.getInt("RoleID"), resultSet.getInt("DepartmentID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Position"), resultSet.getDouble("Salary"), resultSet.getString("RoleName"), resultSet.getString("DepartmentName")));
            }
        }
        return EmployeeDtos;
    }

    public static List<EmployeeDto> getAllEmployeesByName(String name) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName " +
                "FROM Employee e " +
                "INNER JOIN Role r ON e.RoleID = r.RoleID " +
                "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID " +
                "WHERE e.Name LIKE ?";
        List<EmployeeDto> EmployeeDtos = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%")) {
            while (resultSet.next()) {
                EmployeeDtos.add(new EmployeeDto(resultSet.getInt("EmployeeID"), resultSet.getInt("RoleID"), resultSet.getInt("DepartmentID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Email"), resultSet.getString("Password"), resultSet.getString("Position"), resultSet.getDouble("Salary"), resultSet.getString("RoleName"), resultSet.getString("DepartmentName")));
            }
        }
        return EmployeeDtos;
    }
}