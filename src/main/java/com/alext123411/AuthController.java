package com.alext123411;

import com.alext123411.DTO.GitHubCallBack;
import com.alext123411.DTO.LoginRequest;
import com.alext123411.DTO.LoginResponse;
import com.alext123411.DTO.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {

    @Autowired
    AuthService service;

    @GetMapping("/public/test")
    public String check() {
        return "It works!";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/public/oauth2/code/github")
    public ResponseEntity<String> loginGithub(@RequestParam String code) throws AccessDeniedException {
        return ResponseEntity.ok(service.loginGithub(code));
    }
}
