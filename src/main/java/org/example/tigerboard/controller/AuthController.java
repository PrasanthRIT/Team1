package org.example.tigerboard.controller;

import org.example.tigerboard.model.LoginRequest;
import org.example.tigerboard.model.LoginResponse;
import org.example.tigerboard.model.User;
import org.example.tigerboard.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:63342", "http://127.0.0.1:63342"})
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        User loggedInUser = authService.login(request.getEmailID(), request.getPassword());

        if (loggedInUser == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(headers)
                    .body(LoginResponse.failure("Invalid email or password"));
        }

        LoginResponse response = LoginResponse.success(
                loggedInUser.getId(),
                loggedInUser.getEmailID(),
                loggedInUser.getFirstName(),
                loggedInUser.getLastName(),
                loggedInUser.getUserRole().name()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Login successful");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }
}