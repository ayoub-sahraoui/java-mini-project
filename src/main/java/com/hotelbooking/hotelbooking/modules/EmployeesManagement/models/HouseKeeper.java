package com.hotelbooking.hotelbooking.modules.EmployeesManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "house_keeper")
@PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")
public class HouseKeeper extends User{

    public HouseKeeper(String firstName, String lastName, String email, String password, Role role) {
        super(firstName, lastName, email, password, role);
    }

    public HouseKeeper() {

    }
}
