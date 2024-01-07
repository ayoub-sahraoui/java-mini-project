package com.hotelbooking.hotelbooking.modules.room.controller;

import com.hotelbooking.hotelbooking.modules.room.model.RoomBookingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomBookingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomBookingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.room.service.RoomBookingService;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomBookingNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomBookings")
public class RoomBookingController extends ResponseEntityExceptionHandler {

    @Autowired
    private RoomBookingService roomBookingService;

    // Get all roomBookings
    @GetMapping
    public ResponseEntity<List<RoomBookingDTO>> getAllRoomBookings() {
        List<RoomBookingDTO> roomBookings = roomBookingService.getAllRoomBookings();
        return new ResponseEntity<>(roomBookings, HttpStatus.OK);
    }

    // Get a specific roomBooking by ID
    @GetMapping("/{roomBookingId}")
    public ResponseEntity<Object> getRoomBookingById(@PathVariable Long roomBookingId) {
        try {
            RoomBookingDTO roomBooking = roomBookingService.getRoomBookingById(roomBookingId);
            return new ResponseEntity<>(new APIResponse("success", "RoomBooking retrieved successfully", roomBooking), HttpStatus.OK);
        } catch (RoomBookingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new roomBooking
    @PostMapping
    public ResponseEntity<Object> createRoomBooking(@RequestBody CreateRoomBookingDTO roomBooking) {
        try {
            RoomBookingDTO createdRoomBooking = roomBookingService.createRoomBooking(roomBooking);
            return new ResponseEntity<>(new APIResponse("success", "RoomBooking created successfully", createdRoomBooking), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing roomBooking
    @PutMapping("/{roomBookingId}")
    public ResponseEntity<Object> updateRoomBooking(@PathVariable Long roomBookingId, @RequestBody UpdateRoomBookingDTO roomBooking) {
        try {
            RoomBookingDTO updatedRoomBooking = roomBookingService.updateRoomBooking(roomBookingId, roomBooking);
            return new ResponseEntity<>(new APIResponse("success", "RoomBooking updated successfully", updatedRoomBooking), HttpStatus.OK);
        } catch (RoomBookingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a roomBooking by ID
    @DeleteMapping("/{roomBookingId}")
    public ResponseEntity<Object> deleteRoomBooking(@PathVariable Long roomBookingId) {
        try {
            roomBookingService.deleteRoomBooking(roomBookingId);
            return new ResponseEntity<>(new APIResponse("success", "RoomBooking deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (RoomBookingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
