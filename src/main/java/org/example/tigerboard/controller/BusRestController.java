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

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusRestController {

    private final BusService busService;

    public BusRestController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/api/buses/")
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Buses", String.valueOf(buses.size()));
        headers.add("Result Message", "Buses fetched successfully");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(buses);
    }

    @GetMapping("/api/buses/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Integer id) {
        Bus bus = busService.getBusById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Result Message", "Bus fetched successfully with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bus);
    }

    @GetMapping("/api/buses/search")
    public ResponseEntity<List<Bus>> searchBuses(@RequestParam String keyword) {
        List<Bus> buses = busService.searchByKeyword(keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Buses Found", String.valueOf(buses.size()));
        headers.add("Result Message", "Search completed successfully for keyword: " + keyword);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(buses);
    }

    @PostMapping("/api/buses/")
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus Bus = busService.createBus(bus);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Result Message", "Bus created successfully with id: " + Bus.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(Bus);
    }

    @PutMapping("/api/buses/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Integer id, @RequestBody Bus bus) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Result Message", "Bus created successfully with id: " + busService.getBusById(id));
        busService.updateBus(id, bus);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(busService.getBusById(id));
    }

    @DeleteMapping("/api/buses/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Integer id) {
        busService.deleteBusById(id);
        return ResponseEntity.noContent().build();
    }

}

