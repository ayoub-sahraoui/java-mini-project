package com.hotelbooking.hotelbooking.modules.Room_Key_Management.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyId;

    private String barcode;

    private Date issuedAt;

    @Getter
    private boolean active;

    @Getter
    private boolean isMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public RoomKey(String barcode, Date issuedAt, boolean active, boolean isMaster, Room room) {
        this.barcode = barcode;
        this.issuedAt = issuedAt;
        this.active = active;
        this.isMaster = isMaster;
        this.room = room;
    }


}

