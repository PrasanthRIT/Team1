package org.example.tigerboard.repositories;

import jakarta.transaction.Transactional;
import org.example.tigerboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmailID(String emailID);

    boolean existsByEmailID(String emailID);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.emailID) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchUsers(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.passwordHash = :passwordHash WHERE u.id = :id")
    int updatePasswordById(@Param("id") Integer id, @Param("passwordHash") String passwordHash); // TO change user hash

    void deleteById(Integer id); // Object Integer because it can stay null, until Hibernate assigns a value
}
