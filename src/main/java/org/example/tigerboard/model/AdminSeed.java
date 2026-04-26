package org.example.tigerboard.model;

import org.example.tigerboard.model.Admin;
import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.repositories.AdminRepository;
import org.example.tigerboard.repositories.BusRepository;
import org.example.tigerboard.repositories.DriverRepository;
import org.example.tigerboard.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminSeed {

    private final AdminRepository adminRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final StudentRepository studentRepository;

    public AdminSeed(
            AdminRepository adminRepository,
            BusRepository busRepository,
            DriverRepository driverRepository,
            StudentRepository studentRepository
    ) {
        this.adminRepository = adminRepository;
        this.busRepository = busRepository;
        this.driverRepository = driverRepository;
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init() {

        if (adminRepository.findByEmailID("admin@tigerboard.com").isEmpty()) {
            Admin admin = new Admin();
            admin.setEmailID("admin@tigerboard.com");
            admin.setFirstName("System");
            admin.setLastName("Admin");
            admin.setPasswordHash("admin123");
            adminRepository.save(admin);
        }

        Bus bus1 = busRepository.findById(1).orElseGet(() -> {
            Bus bus = new Bus();
            bus.setBusNumber("TB101");
            bus.setCapacity(40);
            bus.setRoute("City Center");
            bus.setIsActive(true);
            return busRepository.save(bus);
        });

        Bus bus2 = busRepository.findById(2).orElseGet(() -> {
            Bus bus = new Bus();
            bus.setBusNumber("TB102");
            bus.setCapacity(35);
            bus.setRoute("Airport Road");
            bus.setIsActive(true);
            return busRepository.save(bus);
        });

        Bus bus3 = busRepository.findById(3).orElseGet(() -> {
            Bus bus = new Bus();
            bus.setBusNumber("TB103");
            bus.setCapacity(30);
            bus.setRoute("University Route");
            bus.setIsActive(false);
            return busRepository.save(bus);
        });

        if (driverRepository.findByEmailID("driver@tigerboard.com").isEmpty()) {
            Driver driver = new Driver();
            driver.setEmailID("driver@tigerboard.com");
            driver.setFirstName("Ravi");
            driver.setLastName("Kumar");
            driver.setPasswordHash("driver123");
            driver.setLicenseNumber("DL-12345");
            driver.setPhoneNumber("9876543210");

            List<Bus> assignedBuses = new ArrayList<>();
            assignedBuses.add(bus1);
            assignedBuses.add(bus2);
            driver.setAssignedBuses(assignedBuses);

            driverRepository.save(driver);
        }

        if (studentRepository.findByEmailID("student@tigerboard.com").isEmpty()) {
            Student student = new Student();
            student.setEmailID("student@tigerboard.com");
            student.setFirstName("Asha");
            student.setLastName("Reddy");
            student.setPasswordHash("student123");
            student.setLocation("Madinat Zayed");
            student.setCommutePlan(Student.CommutePlan.ROUND_TRIP);
            student.setBusAssigned(bus1);
            student.setTripBooked(bus1);

            studentRepository.save(student);
        }
    }
}