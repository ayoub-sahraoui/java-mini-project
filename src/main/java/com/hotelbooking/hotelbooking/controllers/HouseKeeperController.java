package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.models.HouseKeeper;
import com.hotelbooking.hotelbooking.services.HouseKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeper/")
public class HouseKeeperController {

    @Autowired
    private HouseKeeperService houseKeeperService;

    @GetMapping("/find/{id}")
    public ResponseEntity getById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(houseKeeperService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(houseKeeperService.findByEmail(email));
    }

    @GetMapping("/housekeepers")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(houseKeeperService.getAll());
    }

    @PostMapping("/save")
    public HouseKeeper save (@RequestBody HouseKeeper request){
        return houseKeeperService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return houseKeeperService.delete(id);
    }


}
