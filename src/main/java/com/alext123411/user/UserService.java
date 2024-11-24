package com.alext123411.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByGithubId(Long id) {
        return userRepository.findByGitHubId(id);
    }
}
