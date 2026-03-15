package org.example.tigerboard.model;

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
    private User user; //Linking User and Student Entities
    private Integer assignedBusId;  // stores Bus.id

    private CommutePlan commutePlan; // Restricted to CommutePlan enum values
    private String location;

    //Default constructor
    public Student() { }

    //Constructor

    public Student(User user, Integer assignedBusId, CommutePlan commutePlan, String location) {
        this.user = user;
        this.assignedBusId = assignedBusId;
        this.commutePlan = commutePlan;
        this.location = location;
    }


    //getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAssignedBusId() {
        return assignedBusId;
    }

    public void setAssignedBusId(Integer assignedBusId) {
        this.assignedBusId = assignedBusId;
    }

    public CommutePlan getCommutePlan() {
        return commutePlan;
    }

    public void setCommutePlan(CommutePlan commutePlan) {
        this.commutePlan = commutePlan;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //toString()
    @Override
    public String toString() {
        return "Student{" +
                "user=" + user +
                ", assignedBusId=" + assignedBusId +
                ", commutePlan=" + commutePlan +
                ", location='" + location + '\'' +
                '}';
    }
}
