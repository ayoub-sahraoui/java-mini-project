package com.hotelbooking.hotelbooking.modules.Room_Key_Management.repositories;

import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.Room;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByStatus(RoomStatus status);
}
