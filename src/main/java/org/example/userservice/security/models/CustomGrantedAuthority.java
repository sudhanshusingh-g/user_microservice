package org.example.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Getter
@Setter
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "";
    }
}
