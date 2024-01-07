package com.hotelbooking.hotelbooking.modules.invoice.repository;

import com.hotelbooking.hotelbooking.modules.invoice.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
