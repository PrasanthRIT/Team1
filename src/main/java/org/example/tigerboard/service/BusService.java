package org.example.tigerboard.service;

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.repositories.BusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Integer id) {
        return busRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found with id: " + id));
    }

    public List<Bus> searchByBusNumber(String busNumber) {
        return busRepository.findByBusNumberIgnoreCase(busNumber);
    }

    public List<Bus> searchByRoute(String route) {
        return busRepository.findByRouteContainingIgnoreCase(route);
    }

    public List<Bus> searchByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllBuses();
        }
        String normalizedKeyword = keyword.trim();
        return busRepository.findByKeyword(normalizedKeyword);
    }

    public Bus createBus(Bus bus) {
        bus.setId(null);
        return busRepository.save(bus);
    }

    public Bus updateBus(Integer id, Bus updatedBus) {
        Optional<Bus> existingBusOptional = busRepository.findById(id);
        if (existingBusOptional.isPresent()) {
            Bus existingBus = existingBusOptional.get();
            existingBus.setBusNumber(updatedBus.getBusNumber());
            existingBus.setCapacity(updatedBus.getCapacity());
            existingBus.setRoute(updatedBus.getRoute());
            existingBus.setIsActive(updatedBus.getIsActive());
            return busRepository.save(existingBus);
        }
        return null;
    }

    @Transactional
    public int updateRouteById(Integer id, String route) {
        return busRepository.updateBusRouteById(id, route);
    }

    @Transactional
    public void deleteBusById(Integer id) {
        int deletedRows = busRepository.deleteBusById(id);
        if (deletedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found with id: " + id);
        }
    }
}


