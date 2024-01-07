package com.hotelbooking.hotelbooking.modules.employee.controller;

import com.hotelbooking.hotelbooking.modules.employee.model.GuestDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateGuestDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateGuestDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.employee.service.GuestService;
import com.hotelbooking.hotelbooking.modules.employee.exception.GuestNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController extends ResponseEntityExceptionHandler {

    @Autowired
    private GuestService guestService;

    // Get all guests
    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<GuestDTO> guests = guestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    // Get a specific guest by ID
    @GetMapping("/{guestId}")
    public ResponseEntity<Object> getGuestById(@PathVariable Long guestId) {
        try {
            GuestDTO guest = guestService.getGuestById(guestId);
            return new ResponseEntity<>(new APIResponse("success", "Guest retrieved successfully", guest), HttpStatus.OK);
        } catch (GuestNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new guest
    @PostMapping
    public ResponseEntity<Object> createGuest(@RequestBody CreateGuestDTO guest) {
        try {
            GuestDTO createdGuest = guestService.createGuest(guest);
            return new ResponseEntity<>(new APIResponse("success", "Guest created successfully", createdGuest), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing guest
    @PutMapping("/{guestId}")
    public ResponseEntity<Object> updateGuest(@PathVariable Long guestId, @RequestBody UpdateGuestDTO guest) {
        try {
            GuestDTO updatedGuest = guestService.updateGuest(guestId, guest);
            return new ResponseEntity<>(new APIResponse("success", "Guest updated successfully", updatedGuest), HttpStatus.OK);
        } catch (GuestNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a guest by ID
    @DeleteMapping("/{guestId}")
    public ResponseEntity<Object> deleteGuest(@PathVariable Long guestId) {
        try {
            guestService.deleteGuest(guestId);
            return new ResponseEntity<>(new APIResponse("success", "Guest deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (GuestNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
