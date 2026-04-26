package org.example.tigerboard.service;

import org.example.tigerboard.model.Admin;
import org.example.tigerboard.repositories.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    public Admin createAdmin(Admin admin) {
        admin.setUserRole(Admin.Role.ADMIN);
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Integer id, Admin updatedAdmin) {
        Admin existing = getAdminById(id);
        existing.setFirstName(updatedAdmin.getFirstName());
        existing.setLastName(updatedAdmin.getLastName());
        existing.setEmailID(updatedAdmin.getEmailID());
        existing.setPasswordHash(updatedAdmin.getPasswordHash());
        existing.setUserRole(Admin.Role.ADMIN);
        return adminRepository.save(existing);
    }

    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
}

