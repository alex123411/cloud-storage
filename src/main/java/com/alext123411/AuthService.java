package com.alext123411;

import com.alext123411.DTO.GitHubCallBack;
import com.alext123411.DTO.LoginRequest;
import com.alext123411.DTO.LoginResponse;
import com.alext123411.DTO.RegisterRequest;
import com.alext123411.Integration.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class AuthService {

    private final GitHubService gitHubService;
    private final UserRepo repo;

    public AuthService(
        GitHubService gitHubService,
        UserRepo repo
    ) {
        this.gitHubService = gitHubService;
        this.repo = repo;
    }

    public LoginResponse login(LoginRequest request) {
        // Return session token
        User user = repo.getUserByEmailAndPassword(request.getEmail(), request.getPassword());

        LoginResponse resp = new LoginResponse();
        resp.setAccessToken(user.getUuid().toString());
        resp.setRefreshToken("ref_token");

        return resp;
    }

    public String register(RegisterRequest request) throws Exception {
        // Return true or false
        User user = repo.getUserByEmail(request.getEmail());
        if (user != null) throw new Exception("User already exists");

        UUID uuid = repo.addUser(request.getEmail(), request.getPassword());

        return uuid.toString();
    }

    public String loginGithub(String code) throws AccessDeniedException {
        return gitHubService.getAccessToken(code);
    }

    public String getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication auth =  context.getAuthentication();

        String princ = auth.getPrincipal().toString();

        return princ;

    }
}
