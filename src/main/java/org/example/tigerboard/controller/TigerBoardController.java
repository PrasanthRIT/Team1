package org.example.tigerboard.controller;

import org.example.tigerboard.model.Student;
import org.example.tigerboard.service.TigerBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class TigerBoardController {

    private final TigerBoardService tigerBoardService;

    public TigerBoardController(TigerBoardService tigerBoardService) {
        this.tigerBoardService = tigerBoardService;
    }

    /*
    Controller Methods
     */

    //Student
    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("StdRecord", this.tigerBoardService.getAllStudents());

        return "students";
    }

    @GetMapping("/add-students")
    public String saveStudents(Model model, Student student){

        this.tigerBoardService.saveStudents(student);
        model.addAttribute("StdRecord", this.tigerBoardService.getAllStudents());
        return "redirect:students";
    }





}

