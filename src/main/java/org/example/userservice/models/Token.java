package org.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String tokenValue= UUID.randomUUID().toString();
    @ManyToOne
    private User user;
    private LocalDateTime expiresIn;
}
