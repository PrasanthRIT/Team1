package org.example.tigerboard.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin()  {
        setUserRole(Role.ADMIN);
    }
}
