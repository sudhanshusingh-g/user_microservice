package org.example.userservice.controllers;

import org.example.userservice.DTOs.SignupRequestDTO;
import org.example.userservice.DTOs.UserDTO;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody SignupRequestDTO requestDTO){
        User user =userService.registerUser(
                requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword()
        );
        return new ResponseEntity<>(UserDTO.fromUser(user),HttpStatus.OK);
    }
}
