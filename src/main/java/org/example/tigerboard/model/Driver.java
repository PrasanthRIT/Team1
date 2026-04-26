package org.example.tigerboard.model;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@PrimaryKeyJoinColumn(name = "id")
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
        setUserRole(Role.DRIVER);
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
        this.assignedBuses = assignedBuses == null ? new ArrayList<>() : new ArrayList<>(assignedBuses);
    }

    @JsonProperty(access = Access.WRITE_ONLY)
    @Override
    public String getPasswordHash() {
        return super.getPasswordHash();
    }

    @Transient
    public List<Integer> getAssignedBusIds() {
        return assignedBuses.stream()
                .map(Bus::getId)
                .toList();
    }

    public void setAssignedBusIds(List<Integer> assignedBusIds) {
        if (assignedBusIds == null) {
            this.assignedBuses = new ArrayList<>();
            return;
        }

        List<Bus> buses = new ArrayList<>();
        for (Integer busId : assignedBusIds) {
            if (busId != null) {
                Bus bus = new Bus();
                bus.setId(busId);
                buses.add(bus);
            }
        }
        this.assignedBuses = buses;
    }

    @Transient
    public List<String> getAssignedBusNumbers() {
        return assignedBuses.stream()
                .map(Bus::getBusNumber)
                .toList();
    }

    public void setAssignedBusNumbers(List<String> assignedBusNumbers) {
        if (assignedBusNumbers == null) {
            this.assignedBuses = new ArrayList<>();
            return;
        }

        List<Bus> buses = new ArrayList<>();
        for (String busNumber : assignedBusNumbers) {
            if (busNumber != null && !busNumber.trim().isEmpty()) {
                Bus bus = new Bus();
                bus.setBusNumber(busNumber.trim());
                buses.add(bus);
            }
        }
        this.assignedBuses = buses;
    }

    public void removeAssignedBus(Bus bus) {
        if (bus != null) {
            this.assignedBuses.remove(bus);
        }
    }

}