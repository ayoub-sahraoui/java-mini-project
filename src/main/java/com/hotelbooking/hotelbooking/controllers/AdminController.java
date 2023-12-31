package com.hotelbooking.hotelbooking.controllers;

import com.hotelbooking.hotelbooking.DTO.AdminDTO;
import com.hotelbooking.hotelbooking.DTO.ReceptionistDTO;
import com.hotelbooking.hotelbooking.DTO.UserDTO;
import com.hotelbooking.hotelbooking.models.Admin;
import com.hotelbooking.hotelbooking.models.Receptionist;
import com.hotelbooking.hotelbooking.services.AdminService;
import com.hotelbooking.hotelbooking.services.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/find/{id}")
    public ResponseEntity getById(@PathVariable int id){
        return ResponseEntity.ok(adminService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email){
        return ResponseEntity.ok(adminService.findByEmail(email));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(adminService.getAll());
    }

    @PostMapping("/save")
    public Admin save (@RequestBody Admin request){
        return adminService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return adminService.delete(id);
    }


}
