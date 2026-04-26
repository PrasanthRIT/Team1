package org.example.tigerboard.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class LoginRequest {
    private String emailID;

    @JsonAlias("passwordHash")
    private String password;

    public LoginRequest() {
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}