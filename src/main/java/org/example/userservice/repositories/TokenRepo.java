package org.example.userservice.repositories;

import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TokenRepo extends JpaRepository<Token, UUID> {
    Optional<Token> findByUser(User user);
    Token findByUserAndTokenValue(User user, Token token);
}
