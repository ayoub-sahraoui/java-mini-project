package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.RoomKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomKeyDTO {
    private int keyId;
    private String barcode;
    private Date issuedAt;
    private boolean active;
    private boolean isMaster;
    private RoomDTO room;

    public static RoomKeyDTO toDTO(RoomKey roomKey) {
        RoomKeyDTO roomKeyDTO = RoomKeyDTO.builder()
                .keyId(roomKey.getKeyId())
                .barcode(roomKey.getBarcode())
                .issuedAt(roomKey.getIssuedAt())
                .active(roomKey.isActive())
                .isMaster(roomKey.isMaster())
                .build();

        if (roomKey.getRoom() != null) {
            roomKeyDTO.setRoom(RoomDTO.toDTO(roomKey.getRoom()));
        }

        return roomKeyDTO;
    }
}
