package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SignupDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User register(@Valid @RequestBody SignupDto signupDto) {
        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setRole(signupDto.getRole());
        user.setEmail(signupDto.getEmail());
        user.setPassword(signupDto.getPassword());

        return userService.registerUser(user);
    }

    @PatchMapping("/password")
    public Map<String, String> updatePassword(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Map<String, String> passwords) {
        userService.updatePassword(userDetails.getUsername(), passwords.get("oldPassword"),
                passwords.get("newPassword"));
        return Map.of("message", "SUCCESS");
    }

    @DeleteMapping
    public Map<String, String> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return Map.of("message", "User deleted");
    }
}
