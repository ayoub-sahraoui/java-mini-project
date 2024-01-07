package com.hotelbooking.hotelbooking.modules.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelLocationDTO {
    private String name;
    private String location;
    @JsonIgnoreProperties("locations")
    private Hotel hotel;
}
