package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.User;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private List<RoleDTO> roles;

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.name = user.getName();
        userDTO.email = user.getEmail();
        List<RoleDTO> roles=user.getRoles()
                        .stream()
                                .map(RoleDTO::fromRole)
                                        .toList();
        userDTO.setRoles(roles);
        return userDTO;
    }
}
