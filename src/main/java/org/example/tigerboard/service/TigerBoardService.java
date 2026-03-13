package org.example.tigerboard.service;

import org.example.tigerboard.TigerBoardApplication;
import org.example.tigerboard.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TigerBoardService {
    List<Student> students = new ArrayList<Student>();

    public TigerBoardService(){

    }

    public void seedData(){
        students.add(new Student())
    }



}
