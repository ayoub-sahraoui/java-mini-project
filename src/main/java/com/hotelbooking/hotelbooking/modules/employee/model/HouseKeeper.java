package com.hotelbooking.hotelbooking.modules.employee.model;

import com.hotelbooking.hotelbooking.models.User;
import com.hotelbooking.hotelbooking.modules.room.model.RoomHouseKeeping;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HouseKeeper extends User {
    @OneToMany(mappedBy = "houseKeeper")
    List<RoomHouseKeeping> roomHouseKeepings;
}
