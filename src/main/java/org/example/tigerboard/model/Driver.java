package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class Driver {
    private int userId; //references Driver User from User class
    private String name;
    private String licenseNumber;
    private String[] busAssigned; //drivers code for what bus the driver is driving
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
                 ", busAssigned=[" + String.join(", ", busAssigned) + "]" +
                 ", phoneNumber='" + phoneNumber + "'" +
                 '}';
    }
}




