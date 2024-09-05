package com.alext123411;

import com.alext123411.DTO.GitHubCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Validated
public class Controller {

    @Autowired
    AuthService service;

    // http://localhost:8080/api/v1/auth/oauth2/code/github
    @PostMapping("/oauth2/code/github")
    public ResponseEntity<String> loginGithub(
            @RequestBody GitHubCallBack request
    ) {
        return ResponseEntity.ok(service.loginGithub(request));
    }
}
