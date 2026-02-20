package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

    //Attributes
    private String name;
    private int userId; // References Student User from User class
    private String commutePlan; // "MORNING_ONLY", "EVENING_ONLY", "ROUND_TRIP"
    private String location;


    //getters and setters
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommutePlan() {
        return commutePlan;
    }

    public void setCommutePlan(String commutePlan) {
        this.commutePlan = commutePlan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", commutePlan='" + commutePlan + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
