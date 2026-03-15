package org.example.tigerboard.model;

import org.example.tigerboard.model.User;

public class Driver {
    private User user;
    private String licenseNumber;
    private String[] busAssigned;
    private String phoneNumber;



    public Driver() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String[] getBusAssigned() {
        return busAssigned;
    }

    public void setBusAssigned(String[] busAssigned) {
        this.busAssigned = busAssigned;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setActive(boolean b) {
    }

    public void setLastName(String pork) {
    }

    public void setFirstName(String john) {
    }
}