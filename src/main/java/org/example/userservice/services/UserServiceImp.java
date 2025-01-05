package org.example.userservice.services;

import org.example.userservice.models.Role;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.RoleRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    public UserServiceImp(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        // Find or create the Role
        Role role = roleRepository.findByRole("ADMIN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRole("ADMIN"); // Set role to "ADMIN"
                    return roleRepository.save(newRole); // Save the new role
                });
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    @Override
    public Token loginUser(String email, String password) {
        return null;
    }

    @Override
    public Void logoutUser(Token token) {
        return null;
    }
}
