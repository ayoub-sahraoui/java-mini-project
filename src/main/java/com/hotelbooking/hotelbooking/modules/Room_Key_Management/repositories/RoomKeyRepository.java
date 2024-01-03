package com.hotelbooking.hotelbooking.modules.Room_Key_Management.repositories;

import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.RoomKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomKeyRepository extends JpaRepository<RoomKey, Integer> {

    @Override
    Optional<RoomKey> findById(Integer integer);
    
}


