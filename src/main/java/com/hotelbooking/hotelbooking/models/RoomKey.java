package com.hotelbooking.hotelbooking.models;
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


}

