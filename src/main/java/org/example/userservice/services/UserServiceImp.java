package org.example.userservice.services;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.example.userservice.models.Role;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.RoleRepository;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.example.userservice.utils.GenerateRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private TokenRepository tokenRepository;
    public UserServiceImp(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository,
                          TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public User register(String name, String email, String password) {
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
    public Token login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

            if(user.isEmpty()){
                throw new UsernameNotFoundException("User not found");
            }
            if(!bCryptPasswordEncoder.matches(password, user.get().getPassword())){
                throw new BadCredentialsException("Bad credentials");
            }
            Optional<Token> existingToken=tokenRepository.findByUser(user.get());
            if(existingToken.isPresent()){
                return existingToken.get();
            }
            Token token=createToken();
            token.setUser(user.get());
            tokenRepository.save(token);
            return token;
    }

    @Override
    public void logout(String tokenValue) {
        Optional<Token> existingToken= tokenRepository.findByTokenValue(tokenValue);
        assert existingToken.isPresent();
        tokenRepository.deleteById(existingToken.get().getId());
    }

    @Override
    public User validateToken(Token token) {
        return null;
    }

    public Token createToken(){
        Token token = new Token();
        token.setTokenValue(GenerateRandomString.generate(16));
        token.setExpiresIn(LocalDateTime.now().plusMonths(1));
        return tokenRepository.save(token);
    }
}
