package org.bsuir.jwtproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bsuir.jwtproject.model.User;
import org.bsuir.jwtproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Дополнительная панель админа")
public class AdminController {

    private final UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/make-user-admin")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Выдача админки пользователю")
    public User makeUserAdmin(
            @RequestBody @Valid String username) {
        return userService.setAdmin(userService.getByUsername(username));
    }

    @GetMapping("/all-users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение всех пользователей")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
