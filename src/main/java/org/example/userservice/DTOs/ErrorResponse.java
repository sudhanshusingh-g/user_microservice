package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String status;
    private LocalDateTime timestamp;
    public ErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
