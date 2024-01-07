package com.hotelbooking.hotelbooking.modules.notification.model;

import com.hotelbooking.hotelbooking.modules.employee.model.Guest;
import com.hotelbooking.hotelbooking.modules.room.model.RoomBooking;
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
public class Notification {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate createdOn;
    private String content;
    @ManyToOne
    @JoinColumn(name="roomBooking_id")
    private RoomBooking roomBooking;
}
