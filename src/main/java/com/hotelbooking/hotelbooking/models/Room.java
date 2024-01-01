package com.hotelbooking.hotelbooking.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotelbooking.hotelbooking.repositories.RoomRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomStyle style;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;

    public Room(int roomNumber, RoomStyle style, RoomStatus status, double bookingPrice, boolean isSmoking) {
        this.roomNumber = roomNumber;
        this.style = style;
        this.status = status;
        this.bookingPrice = bookingPrice;
        this.isSmoking = isSmoking;
    }

    public boolean isRoomAvailable() {
        return status == RoomStatus.AVAILABLE;
    }

    public void checkIn() {
        this.status = RoomStatus.OCCUPIED;
    }

    public void checkOut() {
        this.status = RoomStatus.AVAILABLE;
    }


}

