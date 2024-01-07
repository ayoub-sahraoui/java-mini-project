package com.hotelbooking.hotelbooking.modules.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String number;
    private RoomStyle style;
    private RoomStatus status;
    private double price;
}
