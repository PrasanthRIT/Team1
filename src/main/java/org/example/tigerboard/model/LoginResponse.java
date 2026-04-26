package org.example.tigerboard.model;

public class LoginResponse {
    private boolean success;
    private String message;
    private Integer id;
    private String emailID;
    private String firstName;
    private String lastName;
    private String userRole;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, String message, Integer id, String emailID,
                         String firstName, String lastName, String userRole) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.emailID = emailID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public static LoginResponse success(Integer id, String emailID, String firstName,
                                        String lastName, String userRole) {
        return new LoginResponse(true, "Login successful", id, emailID, firstName, lastName, userRole);
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, message, null, null, null, null, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId() {
        return id;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserRole() {
        return userRole;
    }
}