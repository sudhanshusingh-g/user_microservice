package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponse {
    private UserDTO user;
    private TokenDTO token;
}
