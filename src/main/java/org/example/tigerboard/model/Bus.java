package org.example.tigerboard.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Bus {

    private Integer id;  // Unique ID for the bus
    private String busNumber;
    private Integer capacity;
    private String route;
    private Boolean isActive;
    private ArrayList<Driver> drivers;
    private ArrayList<Student> students;

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

    public ArrayList<Driver> getDriver() {
        return drivers;
    }

    public void setDriver(ArrayList<Driver> driver) {
        this.drivers = driver;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", busNumber='" + busNumber + "'" +
                ", capacity=" + capacity +
                ", route='" + route + "'" +
                ", isActive=" + isActive +
                ", drivers=" + drivers +
                ", students=" + students +
                '}';
    }
}

