package com.hotelbooking.hotelbooking.modules.employee.model;

import com.hotelbooking.hotelbooking.models.User;
import com.hotelbooking.hotelbooking.modules.invoice.model.Amenity;
import com.hotelbooking.hotelbooking.modules.invoice.model.Kitchen;
import com.hotelbooking.hotelbooking.modules.invoice.model.RoomRelated;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Server extends User {
    @OneToMany(mappedBy = "server")
    private List<Kitchen> kitchens;
    @OneToMany(mappedBy = "server")
    private List<RoomRelated> roomRelateds;
    @OneToMany(mappedBy = "server")
    private List<Amenity> amenities;
}
