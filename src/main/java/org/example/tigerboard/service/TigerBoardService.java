package org.example.tigerboard.service;

import org.example.tigerboard.TigerBoardApplication;
import org.example.tigerboard.model.Bus;
import org.example.tigerboard.model.Driver;
import org.example.tigerboard.model.Student;
import org.example.tigerboard.model.User;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

@Service
public class TigerBoardService {

    List<User> users = new ArrayList<User>();
    List<Student> students = new ArrayList<Student>();
    List<Driver> drivers = new ArrayList<>();
    List<Bus> buses = new ArrayList<>();

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
        u1.setFirstName("James");
        u1.setLastName("Carter");
        u1.setPasswordHash("hashed_pw_001");

        User u2 = new User();
        u2.setFirstName("Sofia");
        u2.setLastName("Patel");
        u2.setPasswordHash("hashed_pw_002");

        User u3 = new User();
        u3.setFirstName("Marcus");
        u3.setLastName("Lee");
        u3.setPasswordHash("hashed_pw_003");

        //Student Records Initialization


        //Driver Records

        Driver d1 = new Driver();
        d1.setId(1);
        d1.setFirstName("Husain");
        d1.setLastName("Dahod");
        d1.setLicenseNumber("A1");
        d1.setActive(true);

        Driver d2 = new Driver();
        d2.setId(2);
        d2.setFirstName("Manu");
        d2.setLastName("Sharma");
        d2.setLicenseNumber("DD1");
        d2.setActive(true);

        Driver d3 = new Driver();
        d3.setId(3);
        d3.setFirstName("John");
        d3.setLastName("Pork");
        d3.setLicenseNumber("S67");
        d3.setActive(true);

        drivers.add(d1);
        drivers.add(d2);
        drivers.add(d3);



        // Bus Records
        Bus bus1 = new Bus();
        bus1.setId(1);
        bus1.setBusNumber("26");
        bus1.setCapacity(30);
        bus1.setRoute("Sharjah to Dubai");
        bus1.setIsActive(true);

        Bus bus2 = new Bus();
        bus2.setId(2);
        bus2.setBusNumber("25");
        bus2.setCapacity(30);
        bus2.setRoute("Sharjah to Dubai");
        bus2.setIsActive(true);
        bus2.setStudents(new ArrayList<Student>(){{add(s1);add(s2);add(s3);}});
        buses.add(bus1);
        buses.add(bus2);

        //Student Records Initialization

        Student s1 = new Student(u1,bus1, Student.CommutePlan.MORNING_ONLY, "Sahara Center");
        Student s2 = new Student(u2, bus2, Student.CommutePlan.EVENING_ONLY, "Lulu Village");
        //Student s3 = new Student(u3, bus3, Student.CommutePlan.ROUND_TRIP, "Pond Park");

        students.add(s1);
        students.add(s2);
        //students.add(s3);

        //Driver Records



    }
    //Student Methods to Fetch and Save Records

    public List<Student> getAllStudents(){
        return this.students;
    }

    public void saveStudents(Student student){
        this.students.add(student);
    }

    //Driver Methods to Fetch and Save Records

    public List<Driver> getAllDrivers() {
        return this.drivers;
    }

    public void saveDrivers(Driver driver) {
        this.drivers.add(driver);
    }

    public List<User> getAllusers() {
        return this.users;
    }
    //Fetch all Buses
    public List<Bus> getAllBuses() {
        return this.buses;
    }

    //Save Bus with Randomly Generated ID between 1 and 10000
    public void saveBus(Bus bus) {
        Random random = new Random();
        int Id = random.nextInt(10000) + 1;
        bus.setId(Id);
        this.buses.add(bus);
    }

    //Fetch Driver by ID (Assuming ID is correlated to the number of drivers in the list)
    public Driver getDriverById(Integer id){
        for (int i = 0; i<this.drivers.size(); i++){
            Driver currentDriver = this.drivers.get(i);
            if (currentDriver.getId().equals(id)){
                return currentDriver;
            }
        }
        return null;
    }

    //Fetch Student by ID (Assuming ID is correlated to the number of Students in the list)
    public Student getStudentById(Integer id){
        for (int i = 0; i<this.students.size(); i++){
            Student currentStudent = this.students.get(i);
            if (currentStudent.getId().equals(id)){
                return currentStudent;
            }
        }
        return null;
    }

}
