package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public static AdminDTO ToDTO(Admin admin){
        return AdminDTO.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .role(admin.getRole()).build();
    }
}
