package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.Room;
import com.hotelbooking.hotelbooking.models.RoomStatus;
import com.hotelbooking.hotelbooking.models.RoomStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomDTO {
    private Long id;
    private int roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;
    private Set<RoomKeyDTO> roomKeys;

    public static RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .style(room.getStyle())
                .status(room.getStatus())
                .bookingPrice(room.getBookingPrice())
                .isSmoking(room.isSmoking())
                .build();

        if (room.getRoomKeys() != null) {
            roomDTO.setRoomKeys(room.getRoomKeys()
                    .stream()
                    .map(RoomKeyDTO::toDTO)
                    .collect(Collectors.toSet()));
        }

        return roomDTO;
    }
}
