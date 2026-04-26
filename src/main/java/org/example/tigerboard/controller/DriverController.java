package org.example.tigerboard.controller;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "*")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Integer id) {
        return driverService.getDriverById(id);
    }

    @GetMapping("/search")
    public List<Driver> searchDrivers(@RequestParam(required = false) String keyword,
                                      @RequestParam(required = false) String licenseNumber) {
        String query = (keyword != null && !keyword.isBlank()) ? keyword : licenseNumber;
        return driverService.searchDrivers(query);
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.createDriver(driver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.updateDriver(id, driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/buses")
    public List<Bus> getAssignedBuses(@PathVariable Integer id) {
        return driverService.getAssignedBuses(id);
    }

    @GetMapping("/{id}/students")
    public List<Map<String, Object>> getStudentsForDriverBuses(@PathVariable Integer id) {
        return driverService.getStudentsForDriverBuses(id);
    }
}