package com.alext123411.config;


import com.alext123411.dto.GitHubUser;
import com.alext123411.github.GitHubService;
import com.alext123411.user.User;
import com.alext123411.user.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class CustomAuthFilter implements Filter {

    private final GitHubService ghService;
    private final UserService userService;

    public CustomAuthFilter(
            GitHubService ghService,
            UserService userService
    ) {
        this.ghService = ghService;
        this.userService = userService;
    }

    public void printBody(ServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();

        // Read the request body
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Convert the StringBuilder to a String
        String body = requestBody.toString();

        // Do something with the request body
        System.out.println("Request Body: " + body);
    }

    public String getAuthorizationToken(HttpServletRequest request) throws AccessDeniedException {
        String header = request.getHeader("Authorization");
        if (header == null) throw new AccessDeniedException("No Token Provided");
        return header.substring(7);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authorizationToken = getAuthorizationToken(request);

        GitHubUser gitHubUser = ghService.fetchUser(authorizationToken);

        try {
            User user = userService.getUserByGithubId(gitHubUser.getId());
        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

        boolean hasAccess = false;
        if (hasAccess) {

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication =
                    new TestingAuthenticationToken(gitHubUser.getLogin(), "password", "ROLE_USER");
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
            chain.doFilter(request, response);
            return;
        }

        throw new AccessDeniedException("Access Denied");
    }
}
