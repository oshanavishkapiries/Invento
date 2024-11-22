package com.invento.invento.model;

import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentModel {

    public static boolean createDepartment(String name, String location) {
        String sql = "INSERT INTO Department ( Name, Location) VALUES ( ?, ?)";

        try {
            return CrudUtil.execute(sql, name, location);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<DepartmentDto> getAllDepartments() {
        String sql = "SELECT * FROM Department";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            List<DepartmentDto> departments = new ArrayList<>();

            while (resultSet.next()) {
                int departmentId = resultSet.getInt("DepartmentID");
                String name = resultSet.getString("Name");
                String location = resultSet.getString("Location");

                DepartmentDto department = new DepartmentDto(departmentId, name, location);
                departments.add(department);
            }

            return departments;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static DepartmentDto getDepartmentById(int departmentId) {
        String sql = "SELECT * FROM Department WHERE DepartmentID = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, departmentId)) {
            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                String location = resultSet.getString("Location");

                return new DepartmentDto(departmentId, name, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean updateDepartment(int departmentId, String name, String location) {
        String sql = "UPDATE Department SET Name = ?, Location = ? WHERE DepartmentID = ?";

        try {
            return CrudUtil.execute(sql, name, location, departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDepartment(int departmentId) {
        String sql = "DELETE FROM Department WHERE DepartmentID = ?";

        try {
            return CrudUtil.execute(sql, departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<DepartmentDto> searchDepartment(String text) {
        String sql = "SELECT * FROM Department WHERE Name LIKE ?";
        List<DepartmentDto> departments = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + text + "%")) {
            while (resultSet.next()) {
                int departmentId = resultSet.getInt("DepartmentID");
                String name = resultSet.getString("Name");
                String location = resultSet.getString("Location");

                DepartmentDto department = new DepartmentDto(departmentId, name, location);
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

}