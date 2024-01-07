package com.hotelbooking.hotelbooking.modules.employee.repository;

import com.hotelbooking.hotelbooking.modules.employee.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
