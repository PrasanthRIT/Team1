package org.example.tigerboard.controller;


import org.example.tigerboard.model.Student;
import org.example.tigerboard.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    //Autowiring
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET - all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Students", String.valueOf(students.size()));
        headers.add("Message", "Students fetched successfully");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(students);
    }

    // GET - student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Student not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
        Student student = studentOpt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Student fetched successfully with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(student);
    }

    // GET - search by email
    @GetMapping("/search")
    public ResponseEntity<Student> searchStudentByEmail(@RequestParam String email) {
        Optional<Student> studentOpt = studentService.searchStudentByEmail(email);
        if (studentOpt.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Student not found with email: " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
        Student student = studentOpt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Student found for email: " + email);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(student);
    }

    // POST - create new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Student created successfully with id: " + createdStudent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdStudent);
    }

    // PUT - update full student by id
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Student not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Student updated successfully with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updatedStudent);
    }

    // DELETE - student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).build();
        }
    }

    //Student Dashboard Specifics

    // POST - book a trip
    @PostMapping("/{studentId}/book/{busId}")
    public ResponseEntity<Student> bookTrip(@PathVariable Integer studentId, @PathVariable Integer busId) {
        try {
            Student updatedStudent = studentService.bookTrip(studentId, busId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Trip booked successfully. Bus ID: " + busId);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updatedStudent);
        } catch (RuntimeException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
        }
    }

    // DELETE - cancel booked trip
    @DeleteMapping("/{studentId}/cancel")
    public ResponseEntity<Student> cancelTrip(@PathVariable Integer studentId) {
        try {
            Student updatedStudent = studentService.cancelTrip(studentId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", "Trip cancelled successfully.");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updatedStudent);
        } catch (RuntimeException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).build();
        }
    }
}