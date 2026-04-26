package org.example.tigerboard.service;

import org.example.tigerboard.model.User;
import org.example.tigerboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String emailID, String passwordHash) {
        if (emailID == null || emailID.trim().isEmpty()) {
            return null;
        }

        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            return null;
        }

        Optional<User> optionalUser = userRepository.findByEmailID(emailID);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (!user.getPasswordHash().equals(passwordHash)) {
            return null;
        }

        return user;
    }

    public Optional<User> getUserByEmailID(String emailID) {
        return userRepository.findByEmailID(emailID);
    }

    public boolean emailExists(String emailID) {
        return userRepository.existsByEmailID(emailID);
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    public boolean updatePassword(Integer id, String newPasswordHash) {
        if (id == null || newPasswordHash == null || newPasswordHash.trim().isEmpty()) {
            return false;
        }

        return userRepository.updatePasswordById(id, newPasswordHash) > 0;
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}