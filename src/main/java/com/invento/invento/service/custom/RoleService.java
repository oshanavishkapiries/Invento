package com.invento.invento.service.custom;

import com.invento.invento.dto.RoleDto;
import java.util.List;

public interface RoleService {
    boolean createRole(String roleName, String description);
    
    List<RoleDto> getAllRoles();
    
    RoleDto getRoleById(int roleId);
    
    boolean updateRole(int roleId, String roleName, String description);
    
    boolean deleteRole(int roleId);
    
    List<RoleDto> searchRole(String text);
} 