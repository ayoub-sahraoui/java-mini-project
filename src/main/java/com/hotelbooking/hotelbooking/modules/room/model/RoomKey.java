package com.hotelbooking.hotelbooking.modules.room.model;

import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocation;
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
public class RoomKey {
    @Id
    @GeneratedValue
    private int id;
    private String barcode;
    private LocalDate takenAt;
    private boolean isActive;
    private boolean isMaster;
    @ManyToOne
    @JoinColumn(name="roomKey_id")
    private Room room;
}
