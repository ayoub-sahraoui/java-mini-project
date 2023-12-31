package com.hotelbooking.hotelbooking.DTO;

import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.models.Role;
import com.hotelbooking.hotelbooking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public static UserDTO ToDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole()).build();
    }
}