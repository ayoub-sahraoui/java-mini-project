package com.hotelbooking.hotelbooking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "server")
@PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")
public class Server extends User{

    public Server(String firstName, String lastName, String email, String password, Role role) {
        super(firstName, lastName, email, password, role);
    }

    public Server() {

    }
}
