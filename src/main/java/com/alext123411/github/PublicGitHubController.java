package com.alext123411.github;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/v1/public/github")
@Validated
public class PublicGitHubController {

    private final GitHubService gitHubService;

    public PublicGitHubController(
            GitHubService gitHubService
    ) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/login/link")
    public ResponseEntity<String> getAuthorizationLink() {
        return ResponseEntity.ok(gitHubService.getAuthorizationLink());
    }

    @GetMapping("/callback")
    public ResponseEntity<String> getAccessToken(@RequestParam String code) throws AccessDeniedException {
        System.out.println();
        return ResponseEntity.ok(gitHubService.getAccessToken(code));
    }
}
