package org.example.tigerboard.service;

import org.example.tigerboard.model.LoginRequest;
import org.example.tigerboard.model.LoginResponse;
import org.example.tigerboard.model.User;
import org.example.tigerboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        if (request == null || isBlank(request.getEmailID()) || isBlank(request.getPassword())) {
            return LoginResponse.failure("Email and password are required");
        }

        Optional<User> optionalUser = userRepository.findByEmailID(request.getEmailID().trim());
        if (optionalUser.isEmpty()) {
            return LoginResponse.failure("Invalid email or password");
        }

        User user = optionalUser.get();

        if (user.getPasswordHash() == null || !user.getPasswordHash().equals(request.getPassword())) {
            return LoginResponse.failure("Invalid email or password");
        }

        String role;
        if (user.getUserRole() != null) {
            role = user.getUserRole().name();
        } else {
            role = null;
        }

        return LoginResponse.success(
                user.getId(),
                user.getEmailID(),
                user.getFirstName(),
                user.getLastName(),
                role
        );
    }

    public boolean emailExists(String emailID) {
        return !isBlank(emailID) && userRepository.existsByEmailID(emailID.trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}