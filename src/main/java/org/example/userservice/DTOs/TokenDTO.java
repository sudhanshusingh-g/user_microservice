package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TokenDTO {
    private String value;
    private LocalDateTime expiresIn;

    public static TokenDTO fromToken(String token) {
        TokenDTO dto = new TokenDTO();
        dto.value = token;
        dto.expiresIn = LocalDateTime.now().plusMonths(1);
        return dto;
    }
}
