package org.example.tigerboard.dto;

import java.util.ArrayList;
import java.util.List;

public class DriverResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailID;
    private String licenseNumber;
    private String phoneNumber;
    private List<Integer> assignedBusIds = new ArrayList<>();
    private List<String> assignedBusNumbers = new ArrayList<>();

    public DriverResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
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

    public List<String> getAssignedBusNumbers() {
        return assignedBusNumbers;
    }

    public void setAssignedBusNumbers(List<String> assignedBusNumbers) {
        this.assignedBusNumbers = assignedBusNumbers;
    }
}