package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Driver {

    private int userId; //references Driver User from User class
    private String name;
    private String licenseNumber;
    private ArrayList<Bus> busesAssigned; //drivers code for what bus the driver is driving
    private String phoneNumber;


    //getters and setters
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

    public ArrayList<Bus> getBusesAssigned() {
        return busesAssigned;
    }

    public void setBusesAssigned(ArrayList<Bus> busesAssigned) {
        this.busesAssigned = busesAssigned;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
         return "Driver{" +
                 ", userID='" + userId + "'" +
                 ", name='" + name + "'" +
                 ", licenseNumber='" + licenseNumber + "'" +
                 ", busesAssigned=" + busesAssigned +
                 ", phoneNumber='" + phoneNumber + "'" +
                 '}';
    }
}




