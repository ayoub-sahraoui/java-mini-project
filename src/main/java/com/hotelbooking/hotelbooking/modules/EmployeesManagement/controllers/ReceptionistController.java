package com.hotelbooking.hotelbooking.modules.EmployeesManagement.controllers;

import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.Receptionist;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.models.UserDTO;
import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.modules.EmployeesManagement.services.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receptionist")
public class ReceptionistController {

    @Autowired
    private ReceptionistService receptionistService;

    @GetMapping("/find/{id}")
    public ResponseEntity getById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(receptionistService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(receptionistService.findByEmail(email));
    }

    @GetMapping("/receptionists")
    public ResponseEntity<List<UserDTO>>getAll(){
        return ResponseEntity.ok(receptionistService.getAll());
    }

    @PostMapping("/save")
    public Receptionist save (@RequestBody Receptionist request){
        return receptionistService.save(request);
    }

    @PutMapping("/{id}")
    public Receptionist updateReceptionist(@RequestBody Receptionist newReceptionist, @PathVariable int id) throws UserNotFoundException {
        return receptionistService.update(newReceptionist,id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return receptionistService.delete(id);
    }


}
