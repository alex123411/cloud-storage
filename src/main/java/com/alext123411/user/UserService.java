package com.alext123411.user;

import com.alext123411.github.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final GitHubService gitHubService;
    private final UserRepository userRepository;

    public User getUserByGithubId(Long id) throws Exception {
        return userRepository.findByGitHubId(id).orElseThrow(
                () -> new Exception("No User Found in Our System")
        );
    }

    public String getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String princ = auth.getPrincipal().toString();
        return princ;
    }
}
