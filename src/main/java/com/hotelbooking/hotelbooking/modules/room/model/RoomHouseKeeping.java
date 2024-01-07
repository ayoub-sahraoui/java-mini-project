package com.hotelbooking.hotelbooking.modules.room.model;

import com.hotelbooking.hotelbooking.modules.employee.model.HouseKeeper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomHouseKeeping {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    private LocalDate startAt;
    private int duration;
    @ManyToOne
    @JoinColumn(name="houseKeeper_id")
    private HouseKeeper houseKeeper;
}
