package com.invento.invento.model;

import com.invento.invento.dto.RoleDto;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleModel {


    public static boolean createRole(String roleName, String description) {
        String sql = "INSERT INTO Role (RoleName, Description) VALUES (?, ?)";

        try {
            return CrudUtil.execute(sql, roleName, description);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<RoleDto> getAllRoles() {
        String sql = "SELECT * FROM Role";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            List<RoleDto> roles = new ArrayList<>();

            while (resultSet.next()) {
                int roleId = resultSet.getInt("RoleID");
                String roleName = resultSet.getString("RoleName");
                String description = resultSet.getString("Description");

                RoleDto role = new RoleDto(roleId, roleName, description);
                roles.add(role);
            }

            return roles;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static RoleDto getRoleById(int roleId) {
        String sql = "SELECT * FROM Role WHERE RoleID = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, roleId)) {
            if (resultSet.next()) {
                String roleName = resultSet.getString("RoleName");
                String description = resultSet.getString("Description");

                return new RoleDto(roleId, roleName, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static boolean updateRole(int roleId, String roleName, String description) {
        String sql = "UPDATE Role SET RoleName = ?, Description = ? WHERE RoleID = ?";

        try {
            return CrudUtil.execute(sql, roleName, description, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteRole(int roleId) {
        String sql = "DELETE FROM Role WHERE RoleID = ?";

        try {
            return CrudUtil.execute(sql, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<RoleDto> searchRole(String text) {
        String sql = "SELECT * FROM Role WHERE RoleName LIKE ?";
        List<RoleDto> roles = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + text + "%")) {
            while (resultSet.next()) {
                int roleId = resultSet.getInt("RoleID");
                String roleName = resultSet.getString("RoleName");
                String description = resultSet.getString("Description");

                RoleDto role = new RoleDto(roleId, roleName, description);
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }
}
