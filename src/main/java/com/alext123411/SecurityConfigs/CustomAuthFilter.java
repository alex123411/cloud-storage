package com.alext123411.SecurityConfigs;


import com.alext123411.Integration.GitHub;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CustomAuthFilter extends GenericFilterBean {

    @Autowired
    private RestTemplate restTemplate;
    private GitHub ghService;

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        ghService.printProperties();

        String baseUrl = "https://api.github.com/user";

        String accessToken = " ";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response_2 = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class);
        System.out.println(response_2.getBody());
    }

    private Authentication getAuthentication(String token) {
        // Implement your token validation and authentication logic here
        // For example, you could parse the token and return an Authentication object

        String baseUrl = "https://github.com/login/oauth/access_token";
        String clientId = " ";
        String clientSecret = " ";

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("client_id", clientId)
            .queryParam("client_secret", clientSecret)
            .queryParam("code", token)
            .toUriString();

        System.out.println(url);

        String response = restTemplate.getForObject(url, String.class);

        System.out.println(response);

        String accessToken = " ";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response_2 = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response_2.getBody());

//        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        return null; // Replace with actual Authentication object
    }
}
