package com.hotelbooking.hotelbooking.modules.room.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomKeyDTO {
    private String barcode;
    private LocalDate takenAt;
    private boolean isActive;
    private boolean isMaster;
}
