package org.example.tigerboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    public Admin()  {
        setUserRole(Role.ADMIN);
    }
}
