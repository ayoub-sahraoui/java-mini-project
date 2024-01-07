package com.hotelbooking.hotelbooking.modules.invoice.model;

import com.hotelbooking.hotelbooking.modules.employee.model.Server;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomRelated extends RoomCharge{
    @Id
    @GeneratedValue
    private int id;
    private boolean isChargeable;
    private LocalDate requestTime;
    @ManyToOne
    @JoinColumn(name="server_id")
    private Server server;
}
