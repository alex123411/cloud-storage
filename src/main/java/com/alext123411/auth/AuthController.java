package com.alext123411.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @GetMapping("/public/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @GetMapping("/public/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok(authService.login());
    }

    @GetMapping("/public/oauth2/code/github")
    public ResponseEntity<String> loginGithub(@RequestParam String code) throws AccessDeniedException {
        return ResponseEntity.ok(authService.loginGithub(code));
    }

    @GetMapping("/user")
    public ResponseEntity<String> loggedInUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
}
