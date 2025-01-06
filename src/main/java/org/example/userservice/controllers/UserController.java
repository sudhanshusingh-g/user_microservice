package org.example.userservice.controllers;

import org.example.userservice.DTOs.*;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody SignupRequestDTO requestDTO){
        User user =userService.register(
                requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword()
        );
        return new ResponseEntity<>(UserDTO.fromUser(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO){
        Token token=userService.login(requestDTO.getEmail(),requestDTO.getPassword());
        LoginResponseDTO response=new LoginResponseDTO();
        response.setToken(token.getTokenValue());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("token") String value){
        userService.logout(value);
        return new ResponseEntity<>("User has been logged out.",HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<UserDTO> validateToken(TokenDTO tokenDTO){
        return null;
    }
}
