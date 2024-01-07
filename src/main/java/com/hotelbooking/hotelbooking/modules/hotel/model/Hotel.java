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
public class Hotel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "hotel")
    private List<HotelLocation> locations;
}
