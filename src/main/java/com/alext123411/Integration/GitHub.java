package com.alext123411.Integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GitHub {

    @Value("${oauth2.github.client-id}")
    private String clientId;

    @Value("${oauth2.github.client-secret}")
    private String clientSecret;

    private static final String gitHubApiBaseUrl = "https://api.github.com";

    public void printProperties() {
        System.out.println(clientId);
        System.out.println(clientSecret);
        System.out.println(gitHubApiBaseUrl);
    }

    public void fetchUser() {

    }
}
