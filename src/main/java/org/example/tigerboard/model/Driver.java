package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class Driver {
    private Integer id;
    private String name;
    private String licenseNumber;
    private String[] busAssigned; //drivers code for what bus the driver is driving
    private String phoneNumber;


    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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





}




