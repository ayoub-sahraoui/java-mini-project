package com.hotelbooking.hotelbooking.modules.hotel.model;

import com.hotelbooking.hotelbooking.modules.room.model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HotelLocation {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String location;
    @ManyToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;
    @OneToMany(mappedBy = "hotelLocation")
    private List<Room> rooms;
}
