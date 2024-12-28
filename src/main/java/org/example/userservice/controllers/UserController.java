package org.example.userservice.controllers;

import org.example.userservice.DTOs.LoginRequest;
import org.example.userservice.DTOs.LoginResponse;
import org.example.userservice.DTOs.SignupRequest;
import org.example.userservice.DTOs.SignupResponse;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request){
        User user=userService.registerUser(request.getName(),request.getEmail(),request.getPassword());
        SignupResponse response=new SignupResponse();
        response=response.from(user);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return null;
    }
}
