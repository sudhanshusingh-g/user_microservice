package org.example.userservice.services;

import org.example.userservice.DTOs.LoginResponse;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;

import java.util.List;

public interface UserService {
    User registerUser(String name, String email, String password);
    LoginResponse loginUser(String email, String password);
}
