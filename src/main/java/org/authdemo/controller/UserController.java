package org.authdemo.controller;

import lombok.RequiredArgsConstructor;
import org.authdemo.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {
        String username = authentication.getName();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "role", user.getRole()
        ));
    }
}
