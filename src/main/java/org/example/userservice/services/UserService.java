package org.example.userservice.services;

import org.example.userservice.models.Token;
import org.example.userservice.models.User;

public interface UserService {
    User registerUser(String name, String email, String password);
    Token loginUser(String email, String password);
    Void logoutUser(Token token);
}
