package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
    //Attributes
    private String name;
    private int studID; // References Student User
    private String commutePlan; // "MORNING_ONLY", "EVENING_ONLY", "ROUND_TRIP"
    private String location;


//Getters and Setters
    public String getName() {
        return name;
    }

    public int getStudID() {
        return studID;
    }

    public void setStudID(int studID) {
        this.studID = studID;
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
                ", studID=" + studID +
                ", commutePlan='" + commutePlan + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
