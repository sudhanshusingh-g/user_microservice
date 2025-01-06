package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Role;

@Getter
@Setter
public class RoleDTO {
    private String roleName;

    public static RoleDTO fromRole(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleName(role.getRole());
        return dto;
    }
}
