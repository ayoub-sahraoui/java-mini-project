package com.hotelbooking.hotelbooking.modules.repositories;

import com.hotelbooking.hotelbooking.modules.models.HouseKeeper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseKeeperRepository extends JpaRepository<HouseKeeper,Integer> {
    Optional<HouseKeeper> findById(int id);

    Optional<HouseKeeper>findByEmail(String email);
}
