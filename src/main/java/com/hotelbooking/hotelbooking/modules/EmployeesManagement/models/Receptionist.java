package com.hotelbooking.hotelbooking.modules.EmployeesManagement.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "receptionists")
@PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")
public class Receptionist extends User {
    
    public Receptionist() {

    }

    public Receptionist(String firstName, String lastName, String email, String password, Role role) {
        super(firstName, lastName, email, password, role);
    }
}
