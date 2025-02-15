package com.invento.invento.service.custom.impl;

import com.invento.invento.dao.DAOfactory;
import com.invento.invento.dao.DAOfactory.DAOTypes;
import com.invento.invento.dao.custom.DepartmentDAO;
import com.invento.invento.dto.DepartmentDto;
import com.invento.invento.entity.Department;
import com.invento.invento.service.custom.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentDAO departmentDAO;
    
    public DepartmentServiceImpl() {
        this.departmentDAO = DAOfactory.getInstance().getDAO(DAOTypes.DEPARTMENT);
    }
    
    @Override
    public boolean createDepartment(String name, String location) {
        return departmentDAO.createDepartment(name, location);
    }
    
    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentDAO.getAllDepartments().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDAO.getDepartmentById(departmentId);
        return department != null ? mapToDto(department) : null;
    }
    
    @Override
    public boolean updateDepartment(int departmentId, String name, String location) {
        return departmentDAO.updateDepartment(departmentId, name, location);
    }
    
    @Override
    public boolean deleteDepartment(int departmentId) {
        return departmentDAO.deleteDepartment(departmentId);
    }
    
    @Override
    public List<DepartmentDto> searchDepartment(String text) {
        return departmentDAO.searchDepartment(text).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    
    private DepartmentDto mapToDto(Department entity) {
        return new DepartmentDto(
            entity.getDepartmentID(),
            entity.getName(),
            entity.getLocation()
        );
    }
} 