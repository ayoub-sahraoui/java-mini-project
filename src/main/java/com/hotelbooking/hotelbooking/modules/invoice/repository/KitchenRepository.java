package com.hotelbooking.hotelbooking.modules.invoice.repository;

import com.hotelbooking.hotelbooking.modules.invoice.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
