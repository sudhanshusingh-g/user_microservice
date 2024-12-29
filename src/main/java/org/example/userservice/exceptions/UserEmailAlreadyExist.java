package org.example.userservice.exceptions;

public class UserEmailAlreadyExist extends RuntimeException{
    public UserEmailAlreadyExist(String message) {
        super(message);
    }
}
