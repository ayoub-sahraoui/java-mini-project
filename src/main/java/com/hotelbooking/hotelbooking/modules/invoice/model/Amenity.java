package com.hotelbooking.hotelbooking.modules.invoice.model;

import com.hotelbooking.hotelbooking.modules.employee.model.Server;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Amenity extends RoomCharge{
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="server_id")
    private Server server;
}
