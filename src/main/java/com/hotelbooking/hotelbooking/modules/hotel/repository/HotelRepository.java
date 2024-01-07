package com.hotelbooking.hotelbooking.modules.hotel.repository;

import com.hotelbooking.hotelbooking.modules.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
