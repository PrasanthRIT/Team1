package org.example.tigerboard.model;

import org.example.tigerboard.model.User;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User {

    private String licenseNumber;
    private String phoneNumber;
    private List<String> assignedBusNumbers;

    public List<String> getAssignedBusNumbers() { return assignedBusNumbers; }
    public void setAssignedBusNumbers(List<String> assignedBusNumbers) {
        this.assignedBusNumbers = assignedBusNumbers;
    }

    public Driver() {
        setUserRole(Role.DRIVER);
        this.assignedBusNumbers = new ArrayList<String>();
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
}