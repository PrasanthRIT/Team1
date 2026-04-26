package org.example.tigerboard.model;


import org.example.tigerboard.repositories.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AdminSeed {

    private final AdminRepository adminRepository;

    public AdminSeed(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    public void init() {
        if (adminRepository.findByEmailID("admin@tigerboard.com").isEmpty()) {
            Admin admin = new Admin();
            admin.setEmailID("admin@tigerboard.com");
            admin.setFirstName("System");
            admin.setLastName("Admin");
            admin.setPasswordHash("admin123");

            adminRepository.save(admin);
        }
    }
}
