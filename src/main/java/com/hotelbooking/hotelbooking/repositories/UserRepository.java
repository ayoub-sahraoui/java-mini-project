package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
