package org.example.tigerboard.dto;

import java.util.ArrayList;
import java.util.List;

public class DriverRequest {
    private String firstName;
    private String lastName;
    private String emailID;
    private String passwordHash;
    private String licenseNumber;
    private String phoneNumber;
    private List<Integer> assignedBusIds = new ArrayList<>();

    public DriverRequest() {
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

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getAssignedBusIds() {
        return assignedBusIds;
    }

    public void setAssignedBusIds(List<Integer> assignedBusIds) {
        this.assignedBusIds = assignedBusIds;
    }
}