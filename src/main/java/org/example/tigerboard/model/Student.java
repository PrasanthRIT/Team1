package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

    /*
    Enums representing the valid commute plan options for students.
    Restricting commutePlan to predefined constant values only
     */
    public enum CommutePlan {
        MORNING_ONLY,   // Student commutes in the morning only
        EVENING_ONLY,   // Student commutes in the evening only
        ROUND_TRIP      // Student commutes both morning and evening
    }

    //Attributes
    private String name;
    private int userId; // References Student User from User class
    private CommutePlan commutePlan; // Restricted to CommutePlan enum values
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

    public CommutePlan getCommutePlan() {
        return commutePlan;
    }

    public void setCommutePlan(CommutePlan commutePlan) {
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
