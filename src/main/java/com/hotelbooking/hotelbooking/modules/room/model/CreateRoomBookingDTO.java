package com.hotelbooking.hotelbooking.modules.room.model;

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
public class CreateRoomBookingDTO {
    private String reference;
    private LocalDate startAt;
    private int duration;
    private BookingStatus status;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
