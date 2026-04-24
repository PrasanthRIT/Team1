package org.example.tigerboard.model;

public class Student extends User {

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
    private String assignedBus;  // stores Bus.id

    private CommutePlan commutePlan; // Restricted to CommutePlan enum values
    private String location;

    //Default constructor
    public Student() {
        setUserRole(Role.STUDENT);
    }

    //Constructor

    public Student(String firstName, String lastName, String emailID, String passwordHash,
                   String assignedBus, CommutePlan commutePlan, String location) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmailID(emailID);
        setPasswordHash(passwordHash);
        setUserRole(Role.STUDENT);

        this.assignedBus = assignedBus;
        this.commutePlan = commutePlan;
        this.location = location;
    }


    //getters and setters


    public String getAssignedBus() {
        return assignedBus;
    }

    public void setAssignedBus(String assignedBus) {
        this.assignedBus = assignedBus;
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
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmailID() + '\'' +
                ", userRole=" + getUserRole() +
                ", assignedBus='" + assignedBus + '\'' +
                ", commutePlan=" + commutePlan +
                ", location='" + location + '\'' +
                '}';
    }
}
