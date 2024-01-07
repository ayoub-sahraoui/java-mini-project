package com.hotelbooking.hotelbooking.modules.employee.repository;

import com.hotelbooking.hotelbooking.modules.employee.model.HouseKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseKeeperRepository extends JpaRepository<HouseKeeper, Long> {
}
