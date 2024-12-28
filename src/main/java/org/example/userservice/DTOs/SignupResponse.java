package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Role;
import org.example.userservice.models.User;

@Getter
@Setter
public class SignupResponse {
    private String name;
    private String email;
    private RoleResponse role;

    public SignupResponse from(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        Role role=new Role();
        this.role=roleResponse(role.getRole());
        return this;
    }

    private RoleResponse roleResponse(String role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRole("ADMIN");
        return roleResponse;
    }

}
