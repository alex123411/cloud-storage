package com.alext123411;

import com.alext123411.DTO.LoginRequest;
import com.alext123411.DTO.LoginResponse;
import com.alext123411.DTO.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepo repo;

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


}
