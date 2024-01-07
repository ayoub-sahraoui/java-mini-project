package com.hotelbooking.hotelbooking.modules.employee.model;

import com.hotelbooking.hotelbooking.models.User;
import com.hotelbooking.hotelbooking.modules.room.model.RoomBooking;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receptionist extends User {
    @OneToMany(mappedBy = "receptionist")
    private List<RoomBooking> roomBookings;
}
