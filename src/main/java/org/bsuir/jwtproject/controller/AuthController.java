package org.bsuir.jwtproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bsuir.jwtproject.model.AuthRequest;
import org.bsuir.jwtproject.model.JwtAuthenticationResponse;
import org.bsuir.jwtproject.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Регистрация пользователя")
    public JwtAuthenticationResponse signUp(
            @RequestBody @Valid AuthRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Авторизация пользователя")
    public JwtAuthenticationResponse signIn(
            @RequestBody @Valid AuthRequest request) {
        return authenticationService.signIn(request);
    }
}
