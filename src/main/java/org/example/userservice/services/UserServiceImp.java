package org.example.userservice.services;

import org.example.userservice.exceptions.InvalidPasswordException;
import org.example.userservice.exceptions.UserEmailAlreadyExist;
import org.example.userservice.models.Role;
import org.example.userservice.models.User;
import org.example.userservice.repositories.RoleRepository;
import org.example.userservice.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepo userRepository;
    private RoleRepository roleRepository;
    public UserServiceImp(BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepo userRepository,
                          RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
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
