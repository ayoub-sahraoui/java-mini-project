package com.hotelbooking.hotelbooking.modules.Room_Key_Management.DTO;

import com.hotelbooking.hotelbooking.modules.Room_Key_Management.models.RoomKey;
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
    private Long roomId;

    public static RoomKeyDTO toDTO(RoomKey roomKey) {
        return RoomKeyDTO.builder()
                .keyId(roomKey.getKeyId())
                .barcode(roomKey.getBarcode())
                .issuedAt(roomKey.getIssuedAt())
                .active(roomKey.isActive())
                .isMaster(roomKey.isMaster())
                .roomId(roomKey.getRoom().getId())
                .build();
    }
}
