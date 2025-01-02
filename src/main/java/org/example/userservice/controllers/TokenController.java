package org.example.userservice.controllers;

import lombok.Getter;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.models.Token;
import org.example.userservice.repositories.TokenRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private TokenRepo tokenRepository;
    public TokenController(TokenRepo tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader ("Authorization") String token){
        String tokenValue=token.startsWith("Bearer ")?token.substring(7):token;
        Token foundToken=tokenRepository.findByTokenValue(tokenValue)
                .orElseThrow(() -> new InvalidTokenException("Token is invalid."));
        if(foundToken.getExpiresIn().isBefore(LocalDateTime.now())){
            throw new InvalidTokenException("Token is expired.");
        }
        return ResponseEntity.ok(true);
    }
}
