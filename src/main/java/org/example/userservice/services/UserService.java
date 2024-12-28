package org.example.userservice.services;

import org.example.userservice.models.User;

import java.util.List;

public interface UserService {
    User registerUser(String name, String email, String password);
    User loginUser(String email, String password);
}
