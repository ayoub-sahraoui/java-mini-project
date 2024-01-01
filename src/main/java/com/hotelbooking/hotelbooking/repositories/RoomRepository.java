package com.hotelbooking.hotelbooking.repositories;

import com.hotelbooking.hotelbooking.models.Room;
import com.hotelbooking.hotelbooking.models.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByStatus(RoomStatus status);
}
