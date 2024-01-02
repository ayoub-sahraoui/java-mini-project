package com.hotelbooking.hotelbooking.modules.EmployeesManagement.repositories;


import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist,Integer> {
    Optional<Receptionist>findById(int id);

    Optional<Receptionist>findByEmail(String email);
}
