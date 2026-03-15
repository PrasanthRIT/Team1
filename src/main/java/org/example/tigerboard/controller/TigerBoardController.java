    package org.example.tigerboard.controller;

    import org.example.tigerboard.model.Driver;
    import org.example.tigerboard.model.Student;
    import org.example.tigerboard.service.TigerBoardService;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class TigerBoardController {

        private final TigerBoardService tigerBoardService;

        public TigerBoardController(TigerBoardService tigerBoardService) {
            this.tigerBoardService = tigerBoardService;
        }

        /*
        Controller Methods
         */

        //Main Page
        @GetMapping("/")
        public String getHomePage() {
            return "forward:/index.html";
        }

        //Student
        @GetMapping("/students")
        public String getStudents(Model model){
            model.addAttribute("StdList", this.tigerBoardService.getAllStudents());
            return "students";
        }

        @GetMapping("/add-students")
        public String getStudentForm() {
            return "add-students";  // name of your form template file
        }

        @PostMapping("/add-students")
        public String saveStudents(Model model, Student student){

            this.tigerBoardService.saveStudents(student);
            model.addAttribute("StdList", this.tigerBoardService.getAllStudents());
            return "redirect:students";
        }
        //Driver

        @GetMapping("/drivers")
        public String getDrivers(Model model){
            model.addAttribute("driverList", this.tigerBoardService.getAllDrivers());
            return "drivers";
        }

        @GetMapping("/add-drivers")
        public String getDriverForm(){
            return "add-drivers";
        }

        @PostMapping("/add-drivers")
        public String saveDrivers(Model model, Driver driver){

            this.tigerBoardService.saveDrivers(driver);
            model.addAttribute("driverList", this.tigerBoardService.getAllDrivers());
            return "redirect:/drivers";
        }
    }

