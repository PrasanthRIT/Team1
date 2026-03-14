package org.example.tigerboard.service;

import org.example.tigerboard.TigerBoardApplication;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TigerBoardService {
    List<User> users = new ArrayList<User>();
    List<Student> students = new ArrayList<Student>();

    public TigerBoardService(){
            seedData();
    }

    public void seedData(){
        //User Records - PlaceHolders (Note: Manognya, You could Parameterize
        //inside these objects,
        //After Adding a constructor in User Class Scope

//        User u1 = new User();
//        User u2 = new User();
//        User u3 = new User();

        //Placeholder - Delete once Modifying User Class - Replace with Parameterized constructor
        User u1 = new User();
        u1.setFirstName("John");
        u1.setLastName("Doe");
        u1.setPasswordHash("abc123");

        User u2 = new User();
        u2.setFirstName("John");
        u2.setLastName("Doe");
        u2.setPasswordHash("abc123");

        User u3 = new User();
        u3.setFirstName("John");
        u3.setLastName("Doe");
        u3.setPasswordHash("abc123");

        //Student Records Initialization
        Student s1 = new Student(1,u1, Student.CommutePlan.MORNING_ONLY, "Sahara Center");
        Student s2 = new Student(2, u2, Student.CommutePlan.EVENING_ONLY, "Lulu Village");
        Student s3 = new Student(2, u3, Student.CommutePlan.ROUND_TRIP, "Pond Park");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        //Driver and Bus Records

    }
    //Student Methods to Fetch and Save Records

    public List<Student> getAllStudents(){
        return this.students;
    }

    public void saveStudents(Student student){
        this.students.add(student);
    }





}
