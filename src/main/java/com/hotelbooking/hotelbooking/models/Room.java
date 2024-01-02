package com.hotelbooking.hotelbooking.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomKey> roomKeys = new HashSet<>();


    public Room(int roomNumber, RoomStyle style, RoomStatus status, double bookingPrice, boolean isSmoking, Set<RoomKey> roomKeys) {
        this.roomNumber = roomNumber;
        this.style = style;
        this.status = status;
        this.bookingPrice = bookingPrice;
        this.isSmoking = isSmoking;
        this.roomKeys = roomKeys;
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
