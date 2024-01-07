package com.hotelbooking.hotelbooking.modules.room.repository;

import com.hotelbooking.hotelbooking.modules.room.model.RoomHouseKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomHouseKeepingRepository extends JpaRepository<RoomHouseKeeping, Long> {
}
