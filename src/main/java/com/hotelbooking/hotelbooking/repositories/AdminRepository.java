package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin ,Integer> {
    Optional<Admin> findById(int id);

    Optional<Admin>findByEmail(String email);
}
