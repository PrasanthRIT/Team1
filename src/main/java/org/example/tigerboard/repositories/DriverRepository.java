package org.example.tigerboard.repositories;

// Name: Hussain Aliasgar Dahodwala
// ID: 418008681

import org.example.tigerboard.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Optional<Driver> findByLicenseNumber(String licenseNumber);

    Optional<Driver> findByEmailID(String emailID);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByEmailID(String emailID);

    List<Driver> findByLicenseNumberContainingIgnoreCase(String licenseNumber);

    @Query("""
           SELECT d FROM Driver d
           WHERE LOWER(d.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(d.licenseNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
           """)
    List<Driver> searchByKeyword(@Param("keyword") String keyword);

    @Query("""
           SELECT d FROM Driver d
           JOIN d.assignedBuses b
           WHERE b.id = :busId
           """)
    List<Driver> findByAssignedBusId(@Param("busId") Integer busId);

    @Transactional
    @Modifying
    @Query("UPDATE Driver d SET d.phoneNumber = :phoneNumber WHERE d.id = :driverId")
    int updatePhoneNumberById(@Param("driverId") Integer driverId,
                              @Param("phoneNumber") String phoneNumber);

}