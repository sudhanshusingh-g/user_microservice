package org.example.userservice.services;

import org.example.userservice.models.Token;
import org.example.userservice.models.User;

public interface UserService {
    User register(String name, String email, String password);
    Token login(String email, String password);
    void logout(String tokenValue);
    User validateToken(Token token);
}
