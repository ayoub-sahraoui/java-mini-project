package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AdminDTO {
    private String firstName;
    private String lastName;
    private String email;
    private  String password;
    private Role role;
}
