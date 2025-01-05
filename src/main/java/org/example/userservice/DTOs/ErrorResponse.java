package org.example.userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String status;
    public ErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
