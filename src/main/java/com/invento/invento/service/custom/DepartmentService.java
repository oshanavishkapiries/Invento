package com.invento.invento.service.custom;

import com.invento.invento.dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    boolean createDepartment(String name, String location);
    
    List<DepartmentDto> getAllDepartments();
    
    DepartmentDto getDepartmentById(int departmentId);
    
    boolean updateDepartment(int departmentId, String name, String location);
    
    boolean deleteDepartment(int departmentId);
    
    List<DepartmentDto> searchDepartment(String text);
} 