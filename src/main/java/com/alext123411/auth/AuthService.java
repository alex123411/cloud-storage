package com.alext123411.auth;

import com.alext123411.github.GitHubService;
import com.alext123411.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class AuthService {

    private final GitHubService gitHubService;
    private final UserRepository userRepository;

    public AuthService(
            GitHubService gitHubService,
            UserRepository userRepository
    ) {
        this.gitHubService = gitHubService;
        this.userRepository = userRepository;
    }

    public String login() {
        // Return GitHub login link
        return gitHubService.getAuthorizeLink();
    }


    public String loginGithub(String code) throws AccessDeniedException {
        return gitHubService.getAccessToken(code);
    }

    public String getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication auth = context.getAuthentication();

        String princ = auth.getPrincipal().toString();

        return princ;

    }
}
