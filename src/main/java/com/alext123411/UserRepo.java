package com.alext123411;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepo {

    List<User> users;

    UserRepo() {
        users = new ArrayList<>();
        users.add(
                this.createNewUser("test@gmail.com", "alextPSWRD")
        );
    }

    public User createNewUser(String email, String password) {
        User newUser = new User(email, password);
        newUser.setUuid(UUID.randomUUID());
        return newUser;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow();
    }

    public User getUserById(UUID uuid) {
        return users.stream()
                .filter(u -> u.getUuid() == uuid)
                .findFirst()
                .orElseThrow();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public UUID addUser(String email, String password) {
        User user = createNewUser(email, password);
        users.add(user);
        return user.getUuid();
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
