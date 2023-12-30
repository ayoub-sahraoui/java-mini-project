package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.DTO.HouseKeeperDTO;
import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.models.HouseKeeper;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.services.HouseKeeperService;
import com.hotelbooking.hotelbooking.services.ReceptionistService;
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
    public ResponseEntity getById(@PathVariable int id){
        return ResponseEntity.ok(houseKeeperService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email){
        return ResponseEntity.ok(houseKeeperService.findByEmail(email));
    }

    @GetMapping("/housekeepers")
    public List<HouseKeeper> getAll(){
        return houseKeeperService.getAll();
    }

    @PostMapping("/save")
    public HouseKeeper save (@RequestBody HouseKeeperDTO request){
        return houseKeeperService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return houseKeeperService.delete(id);
    }


}
