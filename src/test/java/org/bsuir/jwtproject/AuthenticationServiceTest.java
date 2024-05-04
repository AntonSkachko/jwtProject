package org.bsuir.jwtproject;


import org.bsuir.jwtproject.model.AuthRequest;
import org.bsuir.jwtproject.model.JwtAuthenticationResponse;
import org.bsuir.jwtproject.model.User;
import org.bsuir.jwtproject.model.enums.Role;
import org.bsuir.jwtproject.service.AuthenticationService;
import org.bsuir.jwtproject.service.JwtService;
import org.bsuir.jwtproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        authenticationService = new AuthenticationService(
                userService, jwtService, passwordEncoder, authenticationManager
        );
    }

    @Test
    public void signUp_UserRegistration_Success() {
        // Arrange
        AuthRequest request = new AuthRequest("username", "password");
        User expectedUser = User.builder()
                .username(request.getUsername())
                .password("encodedPassword")
                .role(Role.ROLE_USER)
                .build();
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userService.create(any(User.class))).thenReturn(expectedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("sampleJwtToken");

        // Act
        JwtAuthenticationResponse response = authenticationService.signUp(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getToken());
        assertEquals("sampleJwtToken", response.getToken());
        verify(userService, times(1)).create(any(User.class));
        verify(jwtService, times(1)).generateToken(expectedUser);
    }

    @Test
    public void signUp_UserRegistration_Failure() {
        AuthRequest request = new AuthRequest("username", "password");
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userService.create(any(User.class))).thenThrow(new RuntimeException("Failed to create user"));

        assertThrows(RuntimeException.class, () -> authenticationService.signUp(request));
    }
}


