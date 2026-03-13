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
    private Integer id; // References Student User

    private User user; //Linking User and Student Entities

    private CommutePlan commutePlan; // Restricted to CommutePlan enum values
    private String location;

    public Student(Integer id, User user, CommutePlan commutePlan, String location) {
        this.id = id;
        this.user = user;
        this.commutePlan = commutePlan;
        this.location = location;
    }

    //getters and setters

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
                "id=" + id +
                ", user=" + user +
                ", commutePlan=" + commutePlan +
                ", location='" + location + '\'' +
                '}';
    }
}
