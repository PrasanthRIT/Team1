package org.example.tigerboard.repositories;

import org.springframework.transaction.annotation.Transactional;
import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Student;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByBusAssignedId(Integer busId);
    List<Student> findByTripBookedId(Integer busId);

    //Custom JPQL query to filter and search student emailID with case-insensitive matches
    @Query("""
       SELECT s FROM Student s 
       WHERE LOWER(s.emailID) = LOWER(:email)
       """)
    Optional<Student> findByEmailID(@Param("email") String email);

    //Update Logic for tripBooked
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.tripBooked = null WHERE s.id = :studentId")
    Integer cancelTripBooking(@Param("studentId") Integer studentId); //Sets tripBooked to NULL if a trip was cancelled

    //Update Logic for tripBooked
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.tripBooked = :bus WHERE s.id = :studentId")
    Integer updateTripBookedByID(@Param("studentId") Integer studentId, @Param("bus") Bus bus);

    void deleteById(Integer id);

}
