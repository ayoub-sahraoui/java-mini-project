package com.hotelbooking.hotelbooking.modules.room.repository;

import com.hotelbooking.hotelbooking.modules.room.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
}
