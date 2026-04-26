package org.example.tigerboard.controller;

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.service.BusService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:63342", "http://127.0.0.1:63342"})
public class BusRestController {

    private final BusService busService;

    public BusRestController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Busses", String.valueOf(buses.size()));
        headers.add("Message", "Buses fetched successfully");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(buses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Integer id) {
        Bus bus = busService.getBusById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Bus fetched successfully with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bus);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bus>> searchBuses(@RequestParam String keyword) {
        List<Bus> buses = busService.searchByKeyword(keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Buses", String.valueOf(buses.size()));
        headers.add("Message", "Search completed successfully for keyword: " + keyword);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(buses);
    }

    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus createdBus = busService.createBus(bus);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Result Message", "Bus created successfully with id: " + createdBus.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdBus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Integer id, @RequestBody Bus bus) {
        Bus updatedBus = busService.updateBus(id, bus);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Result Message", "Bus updated successfully with id: " + updatedBus.getId());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updatedBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Integer id) {
        busService.deleteBusById(id);
        return ResponseEntity.noContent().build();
    }

}

