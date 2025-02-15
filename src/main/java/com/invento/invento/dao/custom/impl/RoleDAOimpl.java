package com.invento.invento.dao.custom.impl;

import com.invento.invento.dao.custom.RoleDAO;
import com.invento.invento.entity.Role;
import com.invento.invento.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOimpl implements RoleDAO {

    @Override
    public boolean createRole(String roleName, String description) {
        String sql = "INSERT INTO Role (RoleName, Description) VALUES (?, ?)";

        try {
            return CrudUtil.execute(sql, roleName, description);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM Role";

        try (ResultSet resultSet = CrudUtil.execute(sql)) {
            List<Role> roles = new ArrayList<>();

            while (resultSet.next()) {
                roles.add(new Role(
                    resultSet.getInt("RoleID"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("Description")
                ));
            }

            return roles;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Role getRoleById(int roleId) {
        String sql = "SELECT * FROM Role WHERE RoleID = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, roleId)) {
            if (resultSet.next()) {
                return new Role(
                    roleId,
                    resultSet.getString("RoleName"),
                    resultSet.getString("Description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateRole(int roleId, String roleName, String description) {
        String sql = "UPDATE Role SET RoleName = ?, Description = ? WHERE RoleID = ?";

        try {
            return CrudUtil.execute(sql, roleName, description, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(int roleId) {
        String sql = "DELETE FROM Role WHERE RoleID = ?";

        try {
            return CrudUtil.execute(sql, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Role> searchRole(String text) {
        String sql = "SELECT * FROM Role WHERE RoleName LIKE ?";
        List<Role> roles = new ArrayList<>();

        try (ResultSet resultSet = CrudUtil.execute(sql, "%" + text + "%")) {
            while (resultSet.next()) {
                roles.add(new Role(
                    resultSet.getInt("RoleID"),
                    resultSet.getString("RoleName"),
                    resultSet.getString("Description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }
}
