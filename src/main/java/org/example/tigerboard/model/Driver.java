package org.example.tigerboard.model;

import org.apache.catalina.User;

public class Driver {
    private Integer id;
    private User user;
    private String licenseNumber;
    private String[] busAssigned;
    private String phoneNumber;

    public Driver() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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