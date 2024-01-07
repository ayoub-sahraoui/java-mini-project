package com.hotelbooking.hotelbooking.modules.employee.model;

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
public class ServerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
