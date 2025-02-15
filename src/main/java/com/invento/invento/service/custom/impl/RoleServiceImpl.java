package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.RoleDAO;
import com.invento.invento.dto.RoleDto;
import com.invento.invento.entity.Role;
import com.invento.invento.service.custom.RoleService;

import java.util.List;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {
    
    private final RoleDAO roleDAO;
    
    public RoleServiceImpl() {
        this.roleDAO = DAOfactory.getInstance().getDAO(DAOTypes.ROLE);
    }
    
    @Override
    public boolean createRole(String roleName, String description) {
        return roleDAO.createRole(roleName, description);
    }
    
    @Override
    public List<RoleDto> getAllRoles() {
        return roleDAO.getAllRoles().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public RoleDto getRoleById(int roleId) {
        Role role = roleDAO.getRoleById(roleId);
        return role != null ? mapToDto(role) : null;
    }
    
    @Override
    public boolean updateRole(int roleId, String roleName, String description) {
        return roleDAO.updateRole(roleId, roleName, description);
    }
    
    @Override
    public boolean deleteRole(int roleId) {
        return roleDAO.deleteRole(roleId);
    }
    
    @Override
    public List<RoleDto> searchRole(String text) {
        return roleDAO.searchRole(text).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private RoleDto mapToDto(Role entity) {
        return new RoleDto(
            entity.getRoleID(),
            entity.getRoleName(),
            entity.getDescription()
        );
    }
} 