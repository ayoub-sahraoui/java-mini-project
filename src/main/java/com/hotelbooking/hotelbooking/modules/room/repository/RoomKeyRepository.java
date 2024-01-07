package com.hotelbooking.hotelbooking.modules.room.repository;

import com.hotelbooking.hotelbooking.modules.room.model.RoomKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomKeyRepository extends JpaRepository<RoomKey, Long> {
}
