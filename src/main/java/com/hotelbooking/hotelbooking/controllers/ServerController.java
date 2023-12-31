package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.models.Server;
import com.hotelbooking.hotelbooking.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/server/")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @GetMapping("/find/{id}")
    public ResponseEntity getById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(serverService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(serverService.findByEmail(email));
    }

    @GetMapping("/servers")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(serverService.getAll());
    }

    @PostMapping("/save")
    public Server save (@RequestBody Server request){
        return serverService.save(request);
    }

    @PutMapping("/{id}")
    public Server updateServer(@RequestBody Server newServer, @PathVariable int id) throws UserNotFoundException {
        return serverService.update(newServer,id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return serverService.delete(id);
    }


}
