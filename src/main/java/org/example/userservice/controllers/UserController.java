package org.example.userservice.controllers;

import org.example.userservice.DTOs.LoginRequest;
import org.example.userservice.DTOs.LoginResponse;
import org.example.userservice.DTOs.SignupRequest;
import org.example.userservice.DTOs.SignupResponse;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request){
        userService.registerUser(request.getName(),request.getEmail(),request.getPassword());
        SignupResponse response=new SignupResponse();
        response.setMessage("User registered successfully");
        response.setStatus(HttpStatus.CREATED.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = userService.loginUser(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
