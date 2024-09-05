package com.alext123411;

import com.alext123411.DTO.GitHubCallBack;
import com.alext123411.DTO.LoginRequest;
import com.alext123411.DTO.LoginResponse;
import com.alext123411.DTO.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {

    @Autowired
    AuthService service;

    @GetMapping("/")
    public String check() {
        return "Auth servasd";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    // http://localhost:8080/api/v1/auth/oauth2/code/github
    @PostMapping("/oauth2/code/github")
    public ResponseEntity<String> loginGithub(
            @RequestBody GitHubCallBack request,
            @RequestParam String code
    ) {
        System.out.println(code);
        return ResponseEntity.ok(service.loginGithub(request));
    }
}
