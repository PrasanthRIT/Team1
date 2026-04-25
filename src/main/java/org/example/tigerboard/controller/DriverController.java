package org.example.tigerboard.controller;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import org.example.tigerboard.dto.DriverRequest;
import org.example.tigerboard.dto.DriverResponse;
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
    public List<DriverResponse> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public DriverResponse getDriverById(@PathVariable Integer id) {
        return driverService.getDriverById(id);
    }

    @GetMapping("/search")
    public List<DriverResponse> searchDrivers(@RequestParam String keyword) {
        return driverService.searchDrivers(keyword);
    }

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable Integer id, @RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.updateDriver(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/buses")
    public List<Map<String, Object>> getAssignedBuses(@PathVariable Integer id) {
        return driverService.getAssignedBuses(id);
    }

    @GetMapping("/{id}/students")
    public List<Map<String, Object>> getStudentsForDriverBuses(@PathVariable Integer id) {
        return driverService.getStudentsForDriverBuses(id);
    }
}