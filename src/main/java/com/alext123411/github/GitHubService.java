package com.alext123411.github;

import com.alext123411.dto.GitHubUser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;

@Service
public class GitHubService {

    @Value("${oauth2.github.client-id}")
    private String clientId;

    @Value("${oauth2.github.client-secret}")
    private String clientSecret;

    private static final String gitHubApiBaseUrl = "https://api.github.com";
    private static final String gitHubBaseUrl = "https://github.com";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAuthorizationLink() {
        return gitHubBaseUrl + "/login/oauth/authorize?client_id=" + clientId;
    }

    public GitHubUser fetchUser(String token) throws AccessDeniedException {
        String url = gitHubApiBaseUrl + "/user";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate
                    .exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
            throw new AccessDeniedException(ex.getMessage());
        }

        Gson gson = new Gson();

        GitHubUser responseBody = gson.fromJson(response.getBody(), GitHubUser.class);
        return responseBody;
    }

    public String getAccessToken(String code) throws AccessDeniedException {
        String url = gitHubBaseUrl +
                String.format("/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s", clientId, clientSecret, code);

        System.out.println(code);
        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
            throw new AccessDeniedException(ex.getMessage());
        }

        String responseBody = response.getBody();
        System.out.println(responseBody);

        return responseBody;
    }
}
