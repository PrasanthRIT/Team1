//package org.example.tigerboard.service;
//
//import org.example.tigerboard.TigerBoardApplication;
//import org.example.tigerboard.model.Bus;
//import org.example.tigerboard.model.Driver;
//import org.example.tigerboard.model.Student;
//import org.example.tigerboard.model.User;
//import org.springframework.stereotype.Service;
//import java.util.Random;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class TigerBoardService {
//
//    List<User> users = new ArrayList<User>();
//    List<Student> students = new ArrayList<Student>();
//    List<Driver> drivers = new ArrayList<>();
//    List<Bus> buses = new ArrayList<>();
//
//    public TigerBoardService(){
//            seedData();
//    }
//
//    public void addUserWithRole(String firstName, String lastName,
//                                String email, String passwordHash, String role,
//                                String commutePlan, String location,
//                                String assignedBusNumber,
//                                List<String> assignedBusNumbers,
//                                String licenseNumber, String phoneNumber) {
//        User user = new User();
//        user.setId(users.size() + 1);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmailID(email);
//        user.setPasswordHash(passwordHash);
//        user.setUserRole(User.Role.valueOf(role));
//        users.add(user);
//
//        if (role.equals("Student")) {
//            students.add(new Student(
//                    user, assignedBusNumber,
//                    Student.CommutePlan.valueOf(commutePlan),
//                    location
//            ));
//        } else if (role.equals("Driver")) {
//            Driver driver = new Driver();
//            driver.setUser(user);
//            driver.setLicenseNumber(licenseNumber);
//            driver.setPhoneNumber(phoneNumber);
//            if (assignedBusNumbers != null) {
//                driver.setAssignedBusNumbers(assignedBusNumbers);
//            }
//            drivers.add(driver);
//        }
//    }
//
//    public void seedData(){
//        //User Records - PlaceHolders (Note: Manognya, You could Parameterize
//        //inside these objects,
//        //After Adding a constructor in User Class Scope
//
////        User u1 = new User();
////        User u2 = new User();
////        User u3 = new User();
//
//        //Placeholder - Delete once Modifying User Class - Replace with Parameterized constructor
//
//        //Student Records Initialization\
//
//        User u1 = new User();
//        u1.setId(users.size() + 1);
//        u1.setFirstName("James");
//        u1.setLastName("Carter");
//        u1.setEmailID("james.carter@rit.edu");
//        u1.setPasswordHash("hashed_pw_001");
//        u1.setUserRole(User.Role.Student);
//        users.add(u1);
//
//        User u2 = new User();
//        u2.setId(users.size() + 1);
//        u2.setFirstName("Sofia");
//        u2.setLastName("Patel");
//        u2.setEmailID("sofia.patel@rit.edu");
//        u2.setPasswordHash("hashed_pw_002");
//        u2.setUserRole(User.Role.Student);
//        users.add(u2);
//
//
//        User du1 = new User();
//        du1.setId(users.size() + 1);
//        du1.setFirstName("Husain");
//        du1.setLastName("Dahod");
//        du1.setEmailID("husain.dahod@rit.edu");
//        du1.setUserRole(User.Role.Driver);
//        users.add(du1);
//        Driver d1 = new Driver();
//        d1.setUser(du1);
//        d1.setLicenseNumber("A1");
//        d1.setPhoneNumber("050-111-1111");
//        d1.setAssignedBusNumbers(new ArrayList<>(List.of("S7")));
//        drivers.add(d1);
//
//        User du2 = new User();
//        du2.setId(users.size() + 1);
//        du2.setFirstName("Manu");
//        du2.setLastName("Sharma");
//        du2.setEmailID("manu.sharma@rit.edu");
//        du2.setUserRole(User.Role.Driver);
//        users.add(du2);
//        Driver d2 = new Driver();
//        d2.setUser(du2);
//        d2.setLicenseNumber("DD1");
//        d2.setPhoneNumber("050-222-2222");
//        d2.setAssignedBusNumbers(new ArrayList<>(List.of("D1")));
//        drivers.add(d2);
//
//        // Bus Records
//        Bus bus1 = new Bus();
//        bus1.setId(1);
//        bus1.setBusNumber("S7");
//        bus1.setCapacity(30);
//        bus1.setRoute("Sharjah to Dubai");
//        bus1.setIsActive(true);
//
//        Bus bus2 = new Bus();
//        bus2.setId(2);
//        bus2.setBusNumber("D1");
//        bus2.setCapacity(30);
//        bus2.setRoute("Sharjah to Dubai");
//        bus2.setIsActive(true);
//        //bus2.setStudents(new ArrayList<Student>(){{add(s1);add(s2);add(s3);}});
//        buses.add(bus1);
//        buses.add(bus2);
//
//        //Student Records Initialization
//
//        Student s1 = new Student(u1,"S7", Student.CommutePlan.MORNING_ONLY, "Sahara Center");
//        Student s2 = new Student(u2, "D1", Student.CommutePlan.EVENING_ONLY, "Lulu Village");
//        //Student s3 = new Student(u3, bus3, Student.CommutePlan.ROUND_TRIP, "Pond Park");
//
//        students.add(s1);
//        students.add(s2);
//        //students.add(s3);
//
//        //Driver Records
//
//
//
//    }
//    //Student Methods to Fetch and Save Records
//
//    public List<Student> getAllStudents(){
//        return this.students;
//    }
//
//    public void saveStudents(Student student){
//        this.students.add(student);
//    }
//
//    //Driver Methods to Fetch and Save Records
//
//    public List<Driver> getAllDrivers() {
//        return this.drivers;
//    }
//
//    public void saveDrivers(Driver driver) {
//        this.drivers.add(driver);
//    }
//
//    public List<User> getAllusers() {
//        return this.users;
//    }
//    //Fetch all Buses
//    public List<Bus> getAllBuses() {
//        return this.buses;
//    }
//
//    //Save Bus with Randomly Generated ID between 1 and 10000
//    public void saveBus(Bus bus) {
//        Random random = new Random();
//        int Id = random.nextInt(10000) + 1;
//        bus.setId(Id);
//        this.buses.add(bus);
//    }
//
//}
