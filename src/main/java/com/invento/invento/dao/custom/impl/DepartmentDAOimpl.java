package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.DepartmentDAO;
import com.invento.invento.entity.Department;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOimpl implements DepartmentDAO {

    @Override
    public boolean createDepartment(String name, String location) {
        String sql = "INSERT INTO Department ( Name, Location) VALUES ( ?, ?)";

        try {
            return CrudUtil.execute(sql, name, location);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        String sql = "SELECT * FROM Department";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            List<Department> departments = new ArrayList<>();

            while (resultSet.next()) {
                departments.add(new Department(
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Location")
                ));
            }

            return departments;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM Department WHERE DepartmentID = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, departmentId)) {
            if (resultSet.next()) {
                return new Department(
                    departmentId,
                    resultSet.getString("Name"),
                    resultSet.getString("Location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateDepartment(int departmentId, String name, String location) {
        String sql = "UPDATE Department SET Name = ?, Location = ? WHERE DepartmentID = ?";

        try {
            return CrudUtil.execute(sql, name, location, departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        String sql = "DELETE FROM Department WHERE DepartmentID = ?";

        try {
            return CrudUtil.execute(sql, departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Department> searchDepartment(String text) {
        String sql = "SELECT * FROM Department WHERE Name LIKE ?";
        List<Department> departments = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + text + "%")) {
            while (resultSet.next()) {
                departments.add(new Department(
                    resultSet.getInt("DepartmentID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Location")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }
}