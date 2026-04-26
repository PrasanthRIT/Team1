package org.example.tigerboard.repositories;

import org.example.tigerboard.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmailID(String emailID);

}

