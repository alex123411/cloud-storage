package com.alext123411.SecurityConfigs;


import com.alext123411.Integration.GitHubService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;


public class CustomAuthFilter implements Filter {

    GitHubService ghService = new GitHubService();

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

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader("Authorization");
        String token = header.substring(7);


        ghService.fetchUser(token);

        boolean hasAccess = true;
        if (hasAccess) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication =
                new TestingAuthenticationToken("username", "password", "ROLE_USER");
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
            chain.doFilter(request, response);
            return;
        }

        throw new AccessDeniedException("Access Denied");

//        ghService.printProperties();
//
//        String baseUrl = "https://api.github.com/user";
//
//        String accessToken = " ";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + accessToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response_2 = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class);
//        System.out.println(response_2.getBody());
    }
//
//    private Authentication getAuthentication(String token) {
//        // Implement your token validation and authentication logic here
//        // For example, you could parse the token and return an Authentication object
//
//        String baseUrl = "https://github.com/login/oauth/access_token";
//        String clientId = " ";
//        String clientSecret = " ";
//
//        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
//            .queryParam("client_id", clientId)
//            .queryParam("client_secret", clientSecret)
//            .queryParam("code", token)
//            .toUriString();
//
//        System.out.println(url);
//
//        String response = restTemplate.getForObject(url, String.class);
//
//        System.out.println(response);
//
//        String accessToken = " ";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + accessToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response_2 = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println(response_2.getBody());
//
////        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        return null; // Replace with actual Authentication object
//    }
}
