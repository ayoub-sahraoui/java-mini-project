package com.hotelbooking.hotelbooking.modules.employee.repository;

import com.hotelbooking.hotelbooking.modules.employee.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
}
