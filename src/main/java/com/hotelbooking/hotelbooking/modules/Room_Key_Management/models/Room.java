package com.hotelbooking.hotelbooking.modules.Room_Key_Management.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @Getter
    @Setter
    private boolean Smoking;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomKey> roomKeys;

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

