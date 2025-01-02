package org.example.userservice;

import org.example.userservice.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class UserserviceApplicationTests {

    @Autowired
    private JpaRegisteredClientRepository registeredClientRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void addClientRepository() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("ecommerce")
                .clientSecret("$2a$16$60qOfY68Ejfx65RF2g0SmOLzwLx42cRmNPjbrEjxaGiJVDFDJjmJi")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                .scope("ADMIN")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        registeredClientRepository.save(oidcClient);
    }

}
