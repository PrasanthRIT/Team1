package org.example.tigerboard.model;

public class LoginRequest {
    private String emailID;
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