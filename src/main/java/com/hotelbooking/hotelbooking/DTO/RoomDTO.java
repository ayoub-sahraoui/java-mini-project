package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.Room;
import com.hotelbooking.hotelbooking.models.RoomStatus;
import com.hotelbooking.hotelbooking.models.RoomStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static RoomDTO toDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .style(room.getStyle())
                .status(room.getStatus())
                .bookingPrice(room.getBookingPrice())
                .isSmoking(room.isSmoking())
                .build();
    }
}
