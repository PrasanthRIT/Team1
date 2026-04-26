package org.example.tigerboard.repository;

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

    List<Student> findAllByStudentID(Integer id);

    Optional<Student> findByStudentID(Integer id);

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
    Integer cancelTripBooking(@Param("studentId") Integer id); //Sets tripBooked to NULL if a trip was cancelled

    //Update Logic for tripBooked
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.tripBooked = :tripId WHERE s.id = :studentId")
    Integer updateTripBooking(@Param("studentId") Integer id, @Param("tripId") Bus tripBooked); //Sets tripBooked to (bus.id) when trip is booked

    void deleteByStudentID(Integer id);

}
