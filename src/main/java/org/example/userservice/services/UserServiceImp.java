package org.example.userservice.services;

import org.example.userservice.models.User;
import org.example.userservice.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepo userRepository;
    public UserServiceImp(BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserRepo userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
    }

}
