package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.models.RoomKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomKeyRepository extends JpaRepository<RoomKey, Integer> {

    @Override
    Optional<RoomKey> findById(Integer integer);
    
}


