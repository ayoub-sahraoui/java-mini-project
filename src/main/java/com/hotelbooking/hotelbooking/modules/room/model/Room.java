package com.hotelbooking.hotelbooking.modules.room.model;

import com.hotelbooking.hotelbooking.modules.hotel.model.Hotel;
import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue
    private int id;
    private String number;
    private RoomStyle style;
    private RoomStatus status;
    private double price;
    @ManyToOne
    @JoinColumn(name="hotelLocation_id")
    private HotelLocation hotelLocation;
    @OneToMany(mappedBy = "room")
    private List<RoomKey> roomKeys;
}
