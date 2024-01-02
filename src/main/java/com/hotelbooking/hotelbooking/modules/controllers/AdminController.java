package com.hotelbooking.hotelbooking.modules.controllers;


import com.hotelbooking.hotelbooking.exception.UserNotFoundException;
import com.hotelbooking.hotelbooking.modules.models.Admin;
import com.hotelbooking.hotelbooking.modules.models.UserDTO;
import com.hotelbooking.hotelbooking.modules.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/find/{id}")
    public ResponseEntity getById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(adminService.getById(id));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) throws UserNotFoundException{
        return ResponseEntity.ok(adminService.findByEmail(email));
       /** CustomHttpResponse customHttpResponse = new CustomHttpResponse(new Date().toString(), HttpStatus.FOUND.name(), HttpStatus.FOUND.toString(),"","");
        return new  ResponseEntity<>(customHttpResponse , HttpStatus.FOUND);**/
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(adminService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Admin> save (@RequestBody Admin request){
        return new ResponseEntity<>(adminService.save(request),CREATED) ;
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@RequestBody Admin newAdmin,@PathVariable int id) throws UserNotFoundException {
        return adminService.update(newAdmin,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws UserNotFoundException {
        adminService.delete(id);
    }


}