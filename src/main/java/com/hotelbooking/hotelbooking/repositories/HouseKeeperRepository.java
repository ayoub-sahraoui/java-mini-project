package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.models.HouseKeeper;
import com.hotelbooking.hotelbooking.models.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseKeeperRepository extends JpaRepository<HouseKeeper,Integer> {
    Optional<HouseKeeper> findById(int id);

    Optional<HouseKeeper>findByEmail(String email);
}
