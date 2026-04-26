package org.example.tigerboard.model;

//Name: Hussain Aliasgar Dahodwala
//ID: 418008681

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String phoneNumber;
    private List<String> assignedBusNumbers;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "driver_assigned_buses",
            joinColumns = @JoinColumn(name = "driver_id")
    )
    @Column(name = "bus_number")
    private List<String> assignedBusNumbers = new ArrayList<>();

    @Transient
    private User user;

    public Driver() {
        setUserRole(Role.DRIVER);
        this.assignedBusNumbers = new ArrayList<String>();
    }



    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getAssignedBusNumbers() {
        return assignedBusNumbers;
    }

    public void setAssignedBusNumbers(List<String> assignedBusNumbers) {
        this.assignedBusNumbers = assignedBusNumbers == null
                ? new ArrayList<>()
                : assignedBusNumbers;
    }

    @JsonIgnore
    public User getUser() {
        if (user == null) {
            User temp = new User();
            if (id != null) {
                temp.setId(id.intValue());
            }
            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setEmailID(emailID);
            temp.setUserRole(User.Role.Driver);
            user = temp;
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.emailID = user.getEmailID();
        }
    }

    private void syncTransientUser() {
        if (this.user != null) {
            this.user.setFirstName(this.firstName);
            this.user.setLastName(this.lastName);
            this.user.setEmailID(this.emailID);
        }
    }
}