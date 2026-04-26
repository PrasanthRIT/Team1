package org.example.tigerboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;

@Entity
@Table(name="students")
@PrimaryKeyJoinColumn(name = "id")
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

    @Column(nullable = false)
    private String location;

    @Column(name = "commute_plan")
    private CommutePlan commutePlan;

    // Many-to-one unidirectional mapping to Bus entity
    @ManyToOne
    @JoinColumn(name = "bus_assigned_id")
    private Bus busAssigned; // A Foreign key that references Bus(id) which represents the student's bus assigned profile

    @Column(name = "bus_assigned_id", insertable = false, updatable = false)
    private Integer busAssignedId;

    // Many-to-one unidirectional mapping to Bus entity
    @ManyToOne
    @JoinColumn(name = "trip_booked_id")
    private Bus tripBooked; // A Foreign key that references Bus(id) which is initially stored as Null, but updated when student books or Cancels his/her departure trip

    @Column(name = "trip_booked_id", insertable = false, updatable = false)
    private Integer tripBookedId;

    //default constructor
    public Student() {
        setUserRole(Role.STUDENT);
    }

    public Student(String location, CommutePlan commutePlan, Bus busAssigned, Bus tripBooked) {
        this.location = location;
        this.commutePlan = commutePlan;
        this.busAssigned = busAssigned;
        this.tripBooked = tripBooked;
    }

    //getters and setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CommutePlan getCommutePlan() {
        return commutePlan;
    }

    public void setCommutePlan(CommutePlan commutePlan) {
        this.commutePlan = commutePlan;
    }

    public Bus getBusAssigned() {
        return busAssigned;
    }

    public void setBusAssigned(Bus busAssigned) {
        this.busAssigned = busAssigned;
    }

    public Integer getBusAssignedId() {
        return busAssignedId;
    }

    public void setBusAssignedId(Integer busAssignedId) {
        this.busAssignedId = busAssignedId;
    }

    public Bus getTripBooked() {
        return tripBooked;
    }

    public void setTripBooked(Bus tripBooked) {
        this.tripBooked = tripBooked;
    }

    public Integer getTripBookedId() {
        return tripBookedId;
    }

    public void setTripBookedId(Integer tripBookedId) {
        this.tripBookedId = tripBookedId;
    }

    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public String getPasswordHash() {
        return super.getPasswordHash();
    }


    //toString()


    @Override
    public String toString() {
        return "Student{" +
                "location='" + location + '\'' +
                ", commutePlan=" + commutePlan +
                ", busAssigned=" + busAssigned +
                ", busAssignedId=" + busAssignedId +
                ", tripBooked=" + tripBooked +
                ", tripBookedId=" + tripBookedId +
                '}';
    }
}
