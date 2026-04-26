package org.example.tigerboard.service;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.model.User;
import org.example.tigerboard.repositories.BusRepository;
import org.example.tigerboard.repositories.DriverRepository;
import org.example.tigerboard.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final BusRepository busRepository;
    private final StudentRepository studentRepository;

    public DriverService(DriverRepository driverRepository,
                         BusRepository busRepository,
                         StudentRepository studentRepository) {
        this.driverRepository = driverRepository;
        this.busRepository = busRepository;
        this.studentRepository = studentRepository;
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

    public List<Driver> searchDrivers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return driverRepository.findAll();
        }
        return driverRepository.searchByKeyword(keyword.trim());
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
        if (updatedDriver.getPasswordHash() != null && !updatedDriver.getPasswordHash().isBlank()) {
            existing.setPasswordHash(updatedDriver.getPasswordHash());
        }
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

    public List<Bus> getAssignedBuses(Integer driverId) {
        Driver driver = getDriverById(driverId);
        return new ArrayList<>(driver.getAssignedBuses());
    }

    public List<Map<String, Object>> getStudentsForDriverBuses(Integer driverId) {
        Driver driver = getDriverById(driverId);
        List<Integer> busIds = driver.getAssignedBuses().stream()
                .map(Bus::getId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (busIds.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, Map<String, Object>> uniqueStudents = new LinkedHashMap<>();
        for (Integer busId : busIds) {
            List<Student> students = studentRepository.findByBusAssignedId(busId);
            for (Student student : students) {
                Map<String, Object> payload = new LinkedHashMap<>();
                payload.put("id", student.getId());
                payload.put("firstName", student.getFirstName());
                payload.put("lastName", student.getLastName());
                payload.put("emailID", student.getEmailID());
                payload.put("location", student.getLocation());
                payload.put("commutePlan", student.getCommutePlan());
                payload.put("assignedBusNumber", student.getBusAssigned() == null ? null : student.getBusAssigned().getBusNumber());
                uniqueStudents.put(student.getId(), payload);
            }
        }

        return new ArrayList<>(uniqueStudents.values());
    }

    private List<Bus> resolveAssignedBuses(List<Bus> buses) {
        if (buses == null || buses.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> busIds = buses.stream()
                .map(Bus::getId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<String> busNumbers = buses.stream()
                .map(Bus::getBusNumber)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .toList();

        List<Bus> managedBuses = new ArrayList<>(busRepository.findAllById(busIds));
        if (managedBuses.size() != busIds.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more assigned bus IDs are invalid"
            );
        }

        for (String busNumber : busNumbers) {
            boolean alreadyIncluded = managedBuses.stream()
                    .anyMatch(bus -> busNumber.equalsIgnoreCase(bus.getBusNumber()));
            if (alreadyIncluded) {
                continue;
            }

            List<Bus> matches = busRepository.findByBusNumberIgnoreCase(busNumber);
            if (matches.size() != 1) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Assigned bus number is invalid: " + busNumber
                );
            }
            managedBuses.add(matches.get(0));
        }

        return managedBuses;
    }
}