package com.barzhaa.fit.controller;

import com.barzhaa.fit.model.User;
import com.barzhaa.fit.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Регистрация пользователя
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            String result = authService.registerUser(
                    request.email(),
                    request.password(),
                    request.fullName(),
                    request.phone(),
                    request.role()
            );
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Аутентификация пользователя
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<User> user = authService.authenticate(request.email(), request.password());
        return user.isPresent()
                ? ResponseEntity.ok("Аутентификация успешна!")
                : ResponseEntity.status(401).body("Неверный логин или пароль");
    }

    // DTO для регистрации
    public record RegistrationRequest(
            @Email String email,
            @NotBlank String password,
            @NotBlank String fullName,
            @Size(min = 10, max = 15) String phone,
            User.Role role) {}

    // DTO для входа
    public record LoginRequest(
            @Email String email,
            @NotBlank String password) {}
}
