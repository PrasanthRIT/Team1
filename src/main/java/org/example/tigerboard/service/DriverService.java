package org.example.tigerboard.service;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.model.User;
import org.example.tigerboard.repositories.BusRepository;
import org.example.tigerboard.repositories.DriverRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Integer id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Driver not found with id: " + id
                ));
    }

    public List<Driver> searchDriversByLicense(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.isBlank()) {
            return driverRepository.findAll();
        }
        return driverRepository.findByLicenseNumberContainingIgnoreCase(licenseNumber.trim());
    }

    public Driver createDriver(Driver driver) {
        driver.setUserRole(User.Role.DRIVER);
        driver.setAssignedBuses(resolveAssignedBuses(driver.getAssignedBuses()));
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Integer id, Driver updatedDriver) {
        Driver existing = getDriverById(id);

        existing.setFirstName(updatedDriver.getFirstName());
        existing.setLastName(updatedDriver.getLastName());
        existing.setEmailID(updatedDriver.getEmailID());
        existing.setPasswordHash(updatedDriver.getPasswordHash());
        existing.setLicenseNumber(updatedDriver.getLicenseNumber());
        existing.setPhoneNumber(updatedDriver.getPhoneNumber());
        existing.setUserRole(User.Role.DRIVER);
        existing.setAssignedBuses(resolveAssignedBuses(updatedDriver.getAssignedBuses()));

        return driverRepository.save(existing);
    }

    public void deleteDriver(Integer id) {
        Driver existing = getDriverById(id);
        driverRepository.delete(existing);
    }

    public int updateDriverPhoneNumber(Integer id, String phoneNumber) {
        getDriverById(id);
        return driverRepository.updatePhoneNumberById(id, phoneNumber);
    }

    private List<Bus> resolveAssignedBuses(List<Bus> buses) {
        if (buses == null || buses.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> busIds = buses.stream()
                .map(Bus::getId)
                .filter(busId -> busId != null)
                .distinct()
                .toList();

        if (busIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Bus> managedBuses = busRepository.findAllById(busIds);

        if (managedBuses.size() != busIds.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more assigned bus IDs are invalid"
            );
        }

        return new ArrayList<>(managedBuses);
    }
}