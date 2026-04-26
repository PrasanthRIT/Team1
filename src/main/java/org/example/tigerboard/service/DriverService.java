package org.example.tigerboard.service;

//Name: Hussain Aliasgar Dahodwala
//ID: 418008681

import jakarta.annotation.PostConstruct;
import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.repositories.BusRepository;
import org.example.tigerboard.repositories.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final BusRepository busRepository;

    public DriverService(DriverRepository driverRepository, BusRepository busRepository) {
        this.driverRepository = driverRepository;
        this.busRepository = busRepository;
    }

    @PostConstruct
    public void seedDrivers() {
        if (driverRepository.count() > 0) {
            return;
        }

        // Seed assumes buses already exist (recommended architecture order).
        Bus s7 = busRepository.findByBusNumber("S7").orElse(null);
        Bus d1Bus = busRepository.findByBusNumber("D1").orElse(null);

        Driver d1 = new Driver();
        d1.setFirstName("Husain");
        d1.setLastName("Dahod");
        d1.setEmailID("husain.dahod@rit.edu");
        d1.setLicenseNumber("A1");
        d1.setPhoneNumber("050-111-1111");
        if (s7 != null) {
            d1.setAssignedBuses(new ArrayList<>(List.of(s7)));
        } else {
            d1.setAssignedBuses(new ArrayList<>());
        }

        Driver d2 = new Driver();
        d2.setFirstName("Manu");
        d2.setLastName("Sharma");
        d2.setEmailID("manu.sharma@rit.edu");
        d2.setLicenseNumber("DD1");
        d2.setPhoneNumber("050-222-2222");
        if (d1Bus != null) {
            d2.setAssignedBuses(new ArrayList<>(List.of(d1Bus)));
        } else {
            d2.setAssignedBuses(new ArrayList<>());
        }

        driverRepository.saveAll(List.of(d1, d2));
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
    }

    public List<Driver> searchDriversByLicense(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.isBlank()) {
            return driverRepository.findAll();
        }
        return driverRepository.findByLicenseNumberContainingIgnoreCase(licenseNumber);
    }

    public Driver createDriver(Driver driver) {
        if (driver.getAssignedBuses() == null) {
            driver.setAssignedBuses(new ArrayList<>());
        } else {
            // Reattach buses to managed entities by ID
            List<Long> busIds = driver.getAssignedBuses().stream()
                    .map(Bus::getId)
                    .filter(id -> id != null)
                    .toList();
            driver.setAssignedBuses(new ArrayList<>(busRepository.findAllById(busIds)));
        }

        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver updatedDriver) {
        Driver existing = getDriverById(id);

        existing.setFirstName(updatedDriver.getFirstName());
        existing.setLastName(updatedDriver.getLastName());
        existing.setEmailID(updatedDriver.getEmailID());
        existing.setLicenseNumber(updatedDriver.getLicenseNumber());
        existing.setPhoneNumber(updatedDriver.getPhoneNumber());

        if (updatedDriver.getAssignedBuses() == null) {
            existing.setAssignedBuses(new ArrayList<>());
        } else {
            List<Long> busIds = updatedDriver.getAssignedBuses().stream()
                    .map(Bus::getId)
                    .filter(busId -> busId != null)
                    .toList();
            existing.setAssignedBuses(new ArrayList<>(busRepository.findAllById(busIds)));
        }

        return driverRepository.save(existing);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public int updateDriverPhoneNumber(Long id, String phoneNumber) {
        return driverRepository.updatePhoneNumberById(id, phoneNumber);
    }
}