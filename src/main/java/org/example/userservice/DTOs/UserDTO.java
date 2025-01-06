package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Role;
import org.example.userservice.models.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private List<RoleDTO> roles;

    public static UserDTO fromUser(User user) {
        assert user != null;
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(new ArrayList<>());
        for(Role role:user.getRoles()) {
            userDTO.getRoles().add(RoleDTO.fromRole(role));
        }
        return userDTO;
    }

}
