package com.hotelbooking.hotelbooking.modules.hotel.repository;

import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelLocationRepository extends JpaRepository<HotelLocation, Long> {
}
