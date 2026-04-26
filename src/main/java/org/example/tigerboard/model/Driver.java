package org.example.tigerboard.model;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

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

    @ManyToMany
    @JoinTable(
            name = "driver_buses",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id")
    )
    private List<Bus> assignedBuses = new ArrayList<>();

    public Driver() {
        setUserRole(Role.Driver);
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

    public List<Bus> getAssignedBuses() {
        return assignedBuses;
        }

    public void setAssignedBuses(List<Bus> assignedBuses) {
        this.assignedBuses = new ArrayList<>();
        if (assignedBuses != null) {
            this.assignedBuses = assignedBuses;
        }
    }



    public void removeAssignedBus(Bus bus) {
        if (bus != null) {
            this.assignedBuses.remove(bus);
        }
        }

}