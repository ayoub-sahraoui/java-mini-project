package com.hotelbooking.hotelbooking.modules.employee.repository;

import com.hotelbooking.hotelbooking.modules.employee.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
}
