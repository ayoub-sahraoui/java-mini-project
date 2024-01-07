package com.hotelbooking.hotelbooking.modules.room.model;

import com.hotelbooking.hotelbooking.modules.employee.model.Guest;
import com.hotelbooking.hotelbooking.modules.employee.model.Receptionist;
import com.hotelbooking.hotelbooking.modules.invoice.model.Invoice;
import com.hotelbooking.hotelbooking.modules.notification.model.Notification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomBooking {
    @Id
    @GeneratedValue
    private int id;
    private String reference;
    private LocalDate startAt;
    private int duration;
    private BookingStatus status;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;
    @ManyToOne
    @JoinColumn(name="receptionist_id")
    private Receptionist receptionist;
    @OneToMany(mappedBy = "roomBooking")
    List<Notification> notifications;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}
