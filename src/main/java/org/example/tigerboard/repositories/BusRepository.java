package org.example.tigerboard.repositories;

import org.example.tigerboard.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {

    // Find all Busses by IsActive attribute/column
    List<Bus> findAllByIsActive(Boolean isActive);

    List<Bus> findByBusNumberIgnoreCase(
            String busNumber
    );

    List<Bus> findByRouteContainingIgnoreCase(
            String route
    );

    // Custom query used by /api/buses/search?keyword= to find busses by Route or Number
    @Query(""" 
            SELECT b FROM Bus b WHERE LOWER(b.busNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(b.route) LIKE LOWER(CONCAT('%', :keyword, '%'))""")
    List<Bus> findByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query("UPDATE Bus b SET b.route = :route WHERE b.id = :id")
    int updateBusRouteById(@Param("id") Integer id, @Param("route") String route);

    int deleteBusById(Integer id);

}

