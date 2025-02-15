package com.invento.invento.dao.custom;

import com.invento.invento.entity.Department;
import java.util.List;

public interface DepartmentDAO {
    boolean createDepartment(String name, String location);
    
    List<Department> getAllDepartments();
    
    Department getDepartmentById(int departmentId);
    
    boolean updateDepartment(int departmentId, String name, String location);
    
    boolean deleteDepartment(int departmentId);
    
    List<Department> searchDepartment(String text);
} 