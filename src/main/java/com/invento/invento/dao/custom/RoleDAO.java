package com.invento.invento.dao.custom;

import com.invento.invento.entity.Role;
import java.util.List;

public interface RoleDAO {
    boolean createRole(String roleName, String description);
    
    List<Role> getAllRoles();
    
    Role getRoleById(int roleId);
    
    boolean updateRole(int roleId, String roleName, String description);
    
    boolean deleteRole(int roleId);
    
    List<Role> searchRole(String text);
} 