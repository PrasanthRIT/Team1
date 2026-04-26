package org.example.tigerboard.controllers;

import org.example.tigerboard.model.User;
import org.example.tigerboard.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:63342", "http://127.0.0.1:63342"})
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User loggedInUser = authService.login(user.getEmailID(), user.getPasswordHash());

        if (loggedInUser == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body("Login failed");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("userId", loggedInUser.getId());
        response.put("firstName", loggedInUser.getFirstName());
        response.put("lastName", loggedInUser.getLastName());
        response.put("emailID", loggedInUser.getEmailID());
        response.put("role", loggedInUser.getUserRole());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Login successful");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}