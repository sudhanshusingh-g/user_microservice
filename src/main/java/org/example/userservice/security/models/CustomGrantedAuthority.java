package org.example.userservice.security.models;

import lombok.NoArgsConstructor;
import org.example.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority=role.getRole();
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
