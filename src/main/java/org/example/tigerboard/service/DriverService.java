package org.example.tigerboard.service;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.tigerboard.dto.DriverRequest;
import org.example.tigerboard.dto.DriverResponse;
import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.repository.BusRepository;
import org.example.tigerboard.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final BusRepository busRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DriverService(DriverRepository driverRepository, BusRepository busRepository) {
        this.driverRepository = driverRepository;
        this.busRepository = busRepository;
    }

    @PostConstruct
    public void seedDrivers() {
        if (driverRepository.count() > 0) {
            return;
        }

        Bus s7 = getFirstBusByNumber("S7");
        Bus d1 = getFirstBusByNumber("D1");

        if (s7 == null || d1 == null) {
            return;
        }

        Driver driver1 = new Driver();
        driver1.setFirstName("Husain");
        driver1.setLastName("Dahod");
        driver1.setEmailID("husain.dahod@rit.edu");
        driver1.setPasswordHash("driver123");
        driver1.setLicenseNumber("A1");
        driver1.setPhoneNumber("050-111-1111");
        driver1.setAssignedBuses(new ArrayList<>(List.of(s7)));

        Driver driver2 = new Driver();
        driver2.setFirstName("Manu");
        driver2.setLastName("Sharma");
        driver2.setEmailID("manu.sharma@rit.edu");
        driver2.setPasswordHash("driver123");
        driver2.setLicenseNumber("DD1");
        driver2.setPhoneNumber("050-222-2222");
        driver2.setAssignedBuses(new ArrayList<>(List.of(d1)));

        driverRepository.saveAll(List.of(driver1, driver2));
    }

    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DriverResponse getDriverById(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return toResponse(driver);
    }

    public List<DriverResponse> searchDrivers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllDrivers();
        }

        return driverRepository.searchByKeyword(keyword)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DriverResponse createDriver(DriverRequest request) {
        if (driverRepository.existsByEmailID(request.getEmailID())) {
            throw new RuntimeException("Email already exists.");
        }

        if (driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new RuntimeException("License number already exists.");
        }

        Driver driver = new Driver();
        applyRequestToDriver(driver, request);

        return toResponse(driverRepository.save(driver));
    }

    public DriverResponse updateDriver(Integer id, DriverRequest request) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));

        Optional<Driver> existingByLicense = driverRepository.findByLicenseNumber(request.getLicenseNumber());
        if (existingByLicense.isPresent() && existingByLicense.get().getId() != id) {
            throw new RuntimeException("License number already exists.");
        }

        applyRequestToDriver(driver, request);
        return toResponse(driverRepository.save(driver));
    }

    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    public List<Map<String, Object>> getAssignedBuses(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + driverId));

        return driver.getAssignedBuses().stream().map(bus -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", bus.getId());
            map.put("busNumber", bus.getBusNumber());
            map.put("capacity", bus.getCapacity());
            map.put("route", bus.getRoute());
            map.put("isActive", bus.getIsActive());
            return map;
        }).toList();
    }


    public List<Map<String, Object>> getStudentsForDriverBuses(Integer driverId) {
        return new ArrayList<>();
    }


    private void applyRequestToDriver(Driver driver, DriverRequest request) {
        driver.setFirstName(request.getFirstName());
        driver.setLastName(request.getLastName());
        driver.setEmailID(request.getEmailID());
        driver.setPasswordHash(
                request.getPasswordHash() == null || request.getPasswordHash().isBlank()
                        ? "driver123"
                        : request.getPasswordHash()
        );
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setPhoneNumber(request.getPhoneNumber());

        List<Bus> assignedBuses = request.getAssignedBusIds() == null
                ? new ArrayList<>()
                : busRepository.findAllById(request.getAssignedBusIds());

        driver.setAssignedBuses(assignedBuses);
    }

    private DriverResponse toResponse(Driver driver) {
        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setFirstName(driver.getFirstName());
        response.setLastName(driver.getLastName());
        response.setEmailID(driver.getEmailID());
        response.setLicenseNumber(driver.getLicenseNumber());
        response.setPhoneNumber(driver.getPhoneNumber());
        response.setAssignedBusIds(
                driver.getAssignedBuses().stream().map(Bus::getId).toList()
        );
        response.setAssignedBusNumbers(
                driver.getAssignedBuses().stream().map(Bus::getBusNumber).toList()
        );
        return response;
    }

    private Bus getFirstBusByNumber(String busNumber) {
        List<Bus> buses = busRepository.findByBusNumberIgnoreCase(busNumber);
        return buses.isEmpty() ? null : buses.get(0);
    }
}