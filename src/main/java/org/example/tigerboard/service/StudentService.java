package org.example.tigerboard.service;

import org.example.tigerboard.model.Student;
import org.example.tigerboard.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;
    //Find All Students, Find Students By ID and Email, Delete Students By Id Add Booking, Cancel Booking

    public List<Student> getAllStudents(Integer id) {
        return repo.findAllByStudentID(id);
    }

    public Student getStudentsById(Integer id) {
        return repo.findByStudentID(id).orElse(new Student());
    }

    public Student findByEmailID(@Param("email") String email){
        return repo.findByEmailID(email).orElse(new Student());
    }

    public void deleteStudentById(Integer id) {
        repo.deleteByStudentID(id);
    }

}

