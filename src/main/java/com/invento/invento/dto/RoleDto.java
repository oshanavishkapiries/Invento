package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private int roleId;
    private String roleName;
    private String description;

    public RoleDto(int roleId, String roleName, String description) {
        this.roleId = roleId;
        this.description = description;
        this.roleName = roleName;
    }
}
