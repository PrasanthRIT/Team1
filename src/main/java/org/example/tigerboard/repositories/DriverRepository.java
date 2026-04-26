package org.example.tigerboard.repositories;

//Name: Hussain Aliasgar Dahodwala
//ID: 418008681

import org.example.tigerboard.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByLicenseNumberContainingIgnoreCase(String licenseNumber);

    List<Driver> findByLastNameContainingIgnoreCase(String lastName);

    @Query("SELECT d FROM Driver d WHERE LOWER(d.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%'))")
    List<Driver> searchByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Driver d SET d.phoneNumber = :phoneNumber WHERE d.id = :id")
    int updatePhoneNumberById(@Param("id") Long id, @Param("phoneNumber") String phoneNumber);
}