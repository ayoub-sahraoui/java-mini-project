package com.hotelbooking.hotelbooking.modules.employee.controller;

import com.hotelbooking.hotelbooking.modules.employee.model.ReceptionistDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateReceptionistDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateReceptionistDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.employee.service.ReceptionistService;
import com.hotelbooking.hotelbooking.modules.employee.exception.ReceptionistNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/receptionists")
public class ReceptionistController extends ResponseEntityExceptionHandler {

    @Autowired
    private ReceptionistService receptionistService;

    // Get all receptionists
    @GetMapping
    public ResponseEntity<List<ReceptionistDTO>> getAllReceptionists() {
        List<ReceptionistDTO> receptionists = receptionistService.getAllReceptionists();
        return new ResponseEntity<>(receptionists, HttpStatus.OK);
    }

    // Get a specific receptionist by ID
    @GetMapping("/{receptionistId}")
    public ResponseEntity<Object> getReceptionistById(@PathVariable Long receptionistId) {
        try {
            ReceptionistDTO receptionist = receptionistService.getReceptionistById(receptionistId);
            return new ResponseEntity<>(new APIResponse("success", "Receptionist retrieved successfully", receptionist), HttpStatus.OK);
        } catch (ReceptionistNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new receptionist
    @PostMapping
    public ResponseEntity<Object> createReceptionist(@RequestBody CreateReceptionistDTO receptionist) {
        try {
            ReceptionistDTO createdReceptionist = receptionistService.createReceptionist(receptionist);
            return new ResponseEntity<>(new APIResponse("success", "Receptionist created successfully", createdReceptionist), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing receptionist
    @PutMapping("/{receptionistId}")
    public ResponseEntity<Object> updateReceptionist(@PathVariable Long receptionistId, @RequestBody UpdateReceptionistDTO receptionist) {
        try {
            ReceptionistDTO updatedReceptionist = receptionistService.updateReceptionist(receptionistId, receptionist);
            return new ResponseEntity<>(new APIResponse("success", "Receptionist updated successfully", updatedReceptionist), HttpStatus.OK);
        } catch (ReceptionistNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a receptionist by ID
    @DeleteMapping("/{receptionistId}")
    public ResponseEntity<Object> deleteReceptionist(@PathVariable Long receptionistId) {
        try {
            receptionistService.deleteReceptionist(receptionistId);
            return new ResponseEntity<>(new APIResponse("success", "Receptionist deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (ReceptionistNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
