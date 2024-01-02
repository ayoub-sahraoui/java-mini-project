package com.hotelbooking.hotelbooking.modules.repositories;

import com.hotelbooking.hotelbooking.modules.models.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Integer> {
    Optional<Server> findById(int id);

    Optional<Server>findByEmail(String email);
}
