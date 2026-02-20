package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String passwordHash;
    public enum role {
        Student,
        Driver,
        Supervisor,
        Admin
    }
    private role userRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public role getUserRole() {
        return userRole;
    }

    public void setUserRole(role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
