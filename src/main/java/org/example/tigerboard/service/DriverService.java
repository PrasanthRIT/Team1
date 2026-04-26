package org.example.tigerboard.service;

//Name: Hussain Aliasgar Dahodwala
//ID: 418008681

import org.example.tigerboard.model.Driver;
import org.example.tigerboard.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @PostConstruct
    public void seedDrivers() {
        if (driverRepository.count() > 0) {
            return;
        }

        Driver d1 = new Driver();
        d1.setFirstName("Husain");
        d1.setLastName("Dahod");
        d1.setEmailID("husain.dahod@rit.edu");
        d1.setLicenseNumber("A1");
        d1.setPhoneNumber("050-111-1111");
        d1.setAssignedBusNumbers(new ArrayList<>(List.of("S7")));

        Driver d2 = new Driver();
        d2.setFirstName("Manu");
        d2.setLastName("Sharma");
        d2.setEmailID("manu.sharma@rit.edu");
        d2.setLicenseNumber("DD1");
        d2.setPhoneNumber("050-222-2222");
        d2.setAssignedBusNumbers(new ArrayList<>(List.of("D1")));

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
        if (driver.getAssignedBusNumbers() == null) {
            driver.setAssignedBusNumbers(new ArrayList<>());
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
        existing.setAssignedBusNumbers(updatedDriver.getAssignedBusNumbers());

        return driverRepository.save(existing);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public int updateDriverPhoneNumber(Long id, String phoneNumber) {
        return driverRepository.updatePhoneNumberById(id, phoneNumber);
    }
}