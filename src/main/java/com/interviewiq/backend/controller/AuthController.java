package com.interviewiq.backend.controller;

import java.util.Optional;
import com.interviewiq.backend.dto.RegisterRequest;
import com.interviewiq.backend.entity.User;
import com.interviewiq.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return "Welcome " + user.getFullName()
                + "! Registration Successful 🎉";
    }
    @PostMapping("/login")
public String login(@RequestBody RegisterRequest request) {

    Optional<User> user = userRepository.findByEmail(request.getEmail());

    if (user.isEmpty()) {
        return "User not found";
    }

    if (!user.get().getPassword().equals(request.getPassword())) {
        return "Invalid password";
    }

    return "Login Successful! Welcome " + user.get().getFullName() + " 🎉";
}
}