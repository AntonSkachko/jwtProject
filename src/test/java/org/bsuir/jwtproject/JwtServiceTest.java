package org.bsuir.jwtproject;

import org.bsuir.jwtproject.model.User;
import org.bsuir.jwtproject.model.enums.Role;
import org.bsuir.jwtproject.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        jwtService = new JwtService();
        jwtService.setJwtSigningKey("53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855"); // Установите значение jwtSigningKey
        userDetails = createUserDetails();
        userDetails = createUserDetails();
    }

    @Test
    public void testGenerateToken() {
        String token = jwtService.generateToken(userDetails);
        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }

    @Test
    public void testIsTokenValid() {
        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testExtractUserName() {
        String token = jwtService.generateToken(userDetails);
        String userName = jwtService.extractUserName(token);
        Assertions.assertEquals(userDetails.getUsername(), userName);
    }


    private UserDetails createUserDetails() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(Role.ROLE_USER);
        return user;
    }

}