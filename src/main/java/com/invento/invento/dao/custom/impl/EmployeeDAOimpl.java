package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.EmployeeDAO;
import com.invento.invento.entity.Employee;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {

    @Override
    public void createEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (RoleID, DepartmentID, Name, Address, Phone, Email, Password, Position, Salary) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CrudUtil.execute(sql, 
            employee.getRoleID(), 
            employee.getDepartmentID(), 
            employee.getName(),
            employee.getAddress(), 
            employee.getPhone(), 
            employee.getEmail(), 
            employee.getPassword(),
            employee.getPosition(), 
            employee.getSalary()
        );
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET RoleID = ?, DepartmentID = ?, Name = ?, Address = ?, Phone = ?, Email = ?, Password = ?, Position = ?, Salary = ? WHERE EmployeeID = ?";
        CrudUtil.execute(sql, 
            employee.getRoleID(), 
            employee.getDepartmentID(), 
            employee.getName(),
            employee.getAddress(), 
            employee.getPhone(), 
            employee.getEmail(), 
            employee.getPassword(),
            employee.getPosition(), 
            employee.getSalary(), 
            employee.getEmployeeID()
        );
    }

    @Override
    public boolean deleteEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
        return CrudUtil.execute(sql, employeeID);
    }

    @Override
    public Employee getEmployeeById(int employeeID) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName "
                + "FROM Employee e "
                + "INNER JOIN Role r ON e.RoleID = r.RoleID "
                + "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID "
                + "WHERE e.EmployeeID = ?";
        try (ResultSet resultSet = CrudUtil.execute(sql, employeeID)) {
            if (resultSet.next()) {
                return new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getInt("RoleID"),
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Salary"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("DepartmentName")
                );
            }
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName "
                + "FROM Employee e "
                + "INNER JOIN Role r ON e.RoleID = r.RoleID "
                + "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID";
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            while (resultSet.next()) {
                employees.add(new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getInt("RoleID"),
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Salary"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("DepartmentName")
                ));
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployeesByRoleName(String roleName) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName "
                + "FROM Employee e "
                + "INNER JOIN Role r ON e.RoleID = r.RoleID "
                + "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID "
                + "WHERE r.RoleName = ?";
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, roleName)) {
            while (resultSet.next()) {
                employees.add(new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getInt("RoleID"),
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Salary"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("DepartmentName")
                ));
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(String departmentName) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName "
                + "FROM Employee e "
                + "INNER JOIN Role r ON e.RoleID = r.RoleID "
                + "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID "
                + "WHERE d.Name = ?";
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, departmentName)) {
            while (resultSet.next()) {
                employees.add(new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getInt("RoleID"),
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Salary"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("DepartmentName")
                ));
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployeesByName(String name) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.RoleID, e.DepartmentID, e.Name, e.Address, e.Phone, e.Email, e.Password, e.Position, e.Salary, r.RoleName, d.Name AS DepartmentName "
                + "FROM Employee e "
                + "INNER JOIN Role r ON e.RoleID = r.RoleID "
                + "INNER JOIN Department d ON e.DepartmentID = d.DepartmentID "
                + "WHERE e.Name LIKE ?";
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + name + "%")) {
            while (resultSet.next()) {
                employees.add(new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getInt("RoleID"),
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Salary"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("DepartmentName")
                ));
            }
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE Email = ?";
        try (ResultSet resultSet = CrudUtil.execute(sql, email)) {
            if (resultSet.next()) {
                return new Employee(
                    resultSet.getInt("EmployeeID"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password")
                );
            }
        }
        return null;
    }
}