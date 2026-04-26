package org.example.tigerboard.service;

import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.User;
import org.example.tigerboard.repository.BusRepository;
import org.springframework.transaction.annotation.Transactional;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StudentService {

    private StudentRepository studentRepository;
    private BusRepository busRepository;

    public StudentService(StudentRepository studentRepository, BusRepository busRepository) {
        this.studentRepository = studentRepository;
        this.busRepository = busRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> searchStudentByEmail(String email) {
        return studentRepository.findByEmailID(email);
    }

    public Student createStudent(Student student) {
        student.setTripBooked(null);
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Integer id, Student updatedStudent) {
        Optional<Student> existingStudentOpt = studentRepository.findById(id);
        if (existingStudentOpt.isPresent()) {
            Student student = existingStudentOpt.get();

            // Update User fields
            student.setEmailID(updatedStudent.getEmailID());
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setPasswordHash(updatedStudent.getPasswordHash());
            // Role should remain STUDENT – ignore incoming role
            student.setUserRole(User.Role.STUDENT);

            // Update Student-specific fields
            student.setLocation(updatedStudent.getLocation());
            student.setCommutePlan(updatedStudent.getCommutePlan());
            return studentRepository.save(student);
        }
        return null;
    }


    @Transactional
    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

        @Transactional
        public Student bookTrip(Integer studentId, Integer busId) {

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
            Bus bus = busRepository.findById(busId)
                    .orElseThrow(() -> new RuntimeException("Bus not found with id: " + busId));

            if (student.getTripBooked() != null) {
                throw new RuntimeException("Warning: You have already booked a trip (Bus ID: "
                        + student.getTripBooked().getId() + "). Cancel current trip before booking a new one.");
            }

            if (!bus.getIsActive()) {
                throw new RuntimeException("Warning: This bus is currently inactive and cannot be booked.");
            }

            List<Student> studentsWithSameBooking = studentRepository.findByTripBookedId(busId);
            List<Student> studentsWithSameBusAssigned = studentRepository.findByBusAssignedId(busId);

            Set<Student> uniqueSet = new HashSet<>();

            uniqueSet.addAll(studentsWithSameBooking);
            uniqueSet.addAll(studentsWithSameBusAssigned);

            int uniqueCount = uniqueSet.size();

            if (uniqueCount >= bus.getCapacity()) {
                throw new RuntimeException("Warning: This bus has reached full capacity you cannot book at the moment.");
            }

            int updatedRows = studentRepository.updateTripBookedByID(studentId, bus);
            if (updatedRows == 0) {
                throw new RuntimeException("Failed to book trip – student ID may not exist.");
            }

            return studentRepository.findById(studentId).orElseThrow();
        }

    @Transactional
    public Student cancelTrip(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

        if (student.getTripBooked() == null) {
            throw new RuntimeException("No trip booked to cancel.");
        }

        int updated = studentRepository.cancelTripBooking(studentId);

        if (updated == 0) {
            throw new RuntimeException("Cancel failed.");
        }
        return studentRepository.findById(studentId).orElseThrow();
    }

}

