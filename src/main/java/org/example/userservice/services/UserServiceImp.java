package org.example.userservice.services;

import org.example.userservice.DTOs.LoginResponse;
import org.example.userservice.DTOs.TokenDTO;
import org.example.userservice.DTOs.UserDTO;
import org.example.userservice.exceptions.InvalidPasswordException;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.UserEmailAlreadyExist;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Role;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.RoleRepository;
import org.example.userservice.repositories.TokenRepo;
import org.example.userservice.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class UserServiceImp implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepo userRepository;
    private RoleRepository roleRepository;
    private TokenRepo tokenRepository;
    public UserServiceImp(BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepo userRepository,
                          RoleRepository roleRepository,
                          TokenRepo tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User registerUser(String name, String email, String password) {
        if(userRepository.existsByEmail(email)){
            throw new UserEmailAlreadyExist("User email already exist");
        }
        if(!passwordValidation(password)){
            throw new InvalidPasswordException("Invalid password");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        Role defaultRole=roleRepository.findByRole("USER");
        if(defaultRole==null){
            defaultRole=new Role();
            defaultRole.setRole("USER");
            roleRepository.save(defaultRole);
        }
        user.setRoles(Collections.singletonList(defaultRole));
        user= userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponse loginUser(String email, String password) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(
                        ()->new UserNotFoundException("User not found.")
                );
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new InvalidPasswordException("Invalid password");
        }
            Token token=new Token();
            token.setUser(user);
            token.setExpiresIn(LocalDateTime.now().plusMonths(1));
            tokenRepository.save(token);

        LoginResponse response=new LoginResponse();
        response.setToken(TokenDTO.fromToken(token.getTokenValue()));
        response.setUser(UserDTO.fromUser(user));
        return response;
    }

    /**
     * Validates a password based on predefined rules:
     * - Minimum 8 characters, maximum 20 characters
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character
     * - No spaces allowed
     *
     * password the password to validate
     * @return true if the password is valid, false otherwise
     */
    boolean passwordValidation(String password) {
        if(password ==null || password.isEmpty()){
            return false;
        }
        // Regular expression for password validation
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$";
        return password.matches(passwordRegex);
    }

}
