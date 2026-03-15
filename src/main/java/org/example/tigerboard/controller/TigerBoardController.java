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

    @GetMapping("/users/add")
    public String getAddUserPage(Model model) {
        model.addAttribute("isStudent", true);
        model.addAttribute("isDriver", false);
        model.addAttribute("busList", tigerBoardService.getAllBuses());
        return "add-user";
    }

    // Single POST handler for all user types
    @PostMapping("/users/add")
    public String addUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String passwordHash,
            @RequestParam String role,
            @RequestParam(required = false) String commutePlan,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String assignedBusNumber,
            @RequestParam(required = false) List<String> assignedBusNumbers,
            @RequestParam(required = false) String licenseNumber,
            @RequestParam(required = false) String phoneNumber) {

        tigerBoardService.addUserWithRole(
                firstName, lastName, email, passwordHash, role,
                commutePlan, location, assignedBusNumber,
                assignedBusNumbers, licenseNumber, phoneNumber
        );
        return "redirect:/add/success/" + role.toLowerCase();
    }

    //Student
    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("StdList", this.tigerBoardService.getAllStudents());

        return "students";
    }

//        @GetMapping("/students/add")
//        public String getStudentForm() {
//            return "add-students";
//        }

    @GetMapping("/students/add")
    public String getStudentForm(Model model) {
        model.addAttribute("isStudent", true);
        model.addAttribute("isDriver", false);
        model.addAttribute("busList", tigerBoardService.getAllBuses());
        return "add-user";
    }

    @PostMapping("/students/add")
    public String saveStudents(Model model, Student student){

        this.tigerBoardService.saveStudents(student);
        model.addAttribute("StdList", this.tigerBoardService.getAllStudents());
        return "redirect:/students";
    }

    //Driver

    @GetMapping("/drivers")
    public String getDrivers(Model model){
        model.addAttribute("driverList", this.tigerBoardService.getAllDrivers());
        return "drivers";
    }

    @GetMapping("/drivers/add")
    public String getDriverForm(Model model) {
        model.addAttribute("isStudent", false);
        model.addAttribute("isDriver", true);
        model.addAttribute("busList", tigerBoardService.getAllBuses());
        return "add-user";
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


    @PostMapping("/buses/add")
    public String saveBus(Bus bus){
        this.tigerBoardService.saveBus(bus);
        return "redirect:/add/success/buse";
    }

    @GetMapping("/add/success/{entityName}")
    public String showSuccess(@PathVariable String entityName, Model model) {
        model.addAttribute("entityName", entityName);
        model.addAttribute("isBuse", "buse".equalsIgnoreCase(entityName));
        return "success";
    }
    //Adds all students to bus according to their IDs
//        private void addStudentsToBus(Bus bus, ArrayList<Integer> studentsId) {
//            for (int i = 0; i < studentsId.size(); i++) {
//                Integer tempStudentId = studentsId.get(i);
//                Student student = this.tigerBoardService.getStudentById(tempStudentId);
//                if (student != null) {
//                    bus.getStudents().add(student);
//                }
//            }
//        }
//
//        //Adds all drivers to bus according to their IDs
//        private void addDriversToBus(Bus bus, ArrayList<Integer> driversId) {
//            for (int i = 0; i < driversId.size(); i++) {
//                Integer tempDriverId = driversId.get(i);
//                Driver driver = this.tigerBoardService.getDriverById(tempDriverId);
//                if (driver != null) {
//                    bus.getDrivers().add(driver);
//                }
//            }
//        }

}
