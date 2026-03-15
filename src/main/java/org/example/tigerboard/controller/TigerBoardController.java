    package org.example.tigerboard.controller;

    import org.example.tigerboard.model.Bus;
    import org.example.tigerboard.model.Driver;
    import org.example.tigerboard.model.Student;
    import org.example.tigerboard.service.TigerBoardService;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.ArrayList;
    import java.util.List;

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

        @GetMapping("/users")
        public String getUsers(Model model) {
            model.addAttribute("usersList", this.tigerBoardService.getAllusers());

            return "users";
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

        //Buses
        //Fetches all busses to be displayed on buses template
        @GetMapping("/buses")
        public String getBuses(Model model){
            model.addAttribute("busList", this.tigerBoardService.getAllBuses());
            return "buses";
        }

        //Fetches all students and drivers to be displayed on the add-bus template
        @GetMapping("/buses/add")
        public String getBusForm(Model model){
            model.addAttribute("allStudents", this.tigerBoardService.getAllStudents());
            model.addAttribute("allDrivers", this.tigerBoardService.getAllDrivers());
            return "add-bus";
        }

        //Adds a bus with students and drivers based on the IDs selected by the User
        @PostMapping("/buses/add")
        public String saveBus(Bus bus, @RequestParam ArrayList<Integer> studentsId, @RequestParam ArrayList<Integer> driversId){


            this.tigerBoardService.saveBus(bus);

            return "redirect:/add/success/bus";
        }

        //Routes to success page after adding a bus
        @GetMapping("/buses/add/success/{entityName}")
        public String showSuccessPage(Model model, @PathVariable String entityName) {
            model.addAttribute("entity", entityName);
            return "success";
        }



    }

