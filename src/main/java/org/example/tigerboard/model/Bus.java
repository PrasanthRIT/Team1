package org.example.tigerboard.model;

import java.util.ArrayList;

public class Bus {

    private Integer id;  // Unique ID for the bus
    private String busNumber;
    private Integer capacity;
    private String route;
    private Boolean isActive;
    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", busNumber='" + busNumber + "'" +
                ", capacity=" + capacity +
                ", route='" + route + "'" +
                ", isActive=" + isActive +
                '}';
    }
}

