package com.hotelbooking.hotelbooking.modules.room.controller;

import com.hotelbooking.hotelbooking.modules.room.model.RoomHouseKeepingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomHouseKeepingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomHouseKeepingDTO;
import com.hotelbooking.hotelbooking.modules.room.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.room.service.RoomHouseKeepingService;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomHouseKeepingNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomHouseKeepings")
public class RoomHouseKeepingController extends ResponseEntityExceptionHandler {

    @Autowired
    private RoomHouseKeepingService roomHouseKeepingService;

    // Get all roomHouseKeepings
    @GetMapping
    public ResponseEntity<List<RoomHouseKeepingDTO>> getAllRoomHouseKeepings() {
        List<RoomHouseKeepingDTO> roomHouseKeepings = roomHouseKeepingService.getAllRoomHouseKeepings();
        return new ResponseEntity<>(roomHouseKeepings, HttpStatus.OK);
    }

    // Get a specific roomHouseKeeping by ID
    @GetMapping("/{roomHouseKeepingId}")
    public ResponseEntity<Object> getRoomHouseKeepingById(@PathVariable Long roomHouseKeepingId) {
        try {
            RoomHouseKeepingDTO roomHouseKeeping = roomHouseKeepingService.getRoomHouseKeepingById(roomHouseKeepingId);
            return new ResponseEntity<>(new APIResponse("success", "RoomHouseKeeping retrieved successfully", roomHouseKeeping), HttpStatus.OK);
        } catch (RoomHouseKeepingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new roomHouseKeeping
    @PostMapping
    public ResponseEntity<Object> createRoomHouseKeeping(@RequestBody CreateRoomHouseKeepingDTO roomHouseKeeping) {
        try {
            RoomHouseKeepingDTO createdRoomHouseKeeping = roomHouseKeepingService.createRoomHouseKeeping(roomHouseKeeping);
            return new ResponseEntity<>(new APIResponse("success", "RoomHouseKeeping created successfully", createdRoomHouseKeeping), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing roomHouseKeeping
    @PutMapping("/{roomHouseKeepingId}")
    public ResponseEntity<Object> updateRoomHouseKeeping(@PathVariable Long roomHouseKeepingId, @RequestBody UpdateRoomHouseKeepingDTO roomHouseKeeping) {
        try {
            RoomHouseKeepingDTO updatedRoomHouseKeeping = roomHouseKeepingService.updateRoomHouseKeeping(roomHouseKeepingId, roomHouseKeeping);
            return new ResponseEntity<>(new APIResponse("success", "RoomHouseKeeping updated successfully", updatedRoomHouseKeeping), HttpStatus.OK);
        } catch (RoomHouseKeepingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a roomHouseKeeping by ID
    @DeleteMapping("/{roomHouseKeepingId}")
    public ResponseEntity<Object> deleteRoomHouseKeeping(@PathVariable Long roomHouseKeepingId) {
        try {
            roomHouseKeepingService.deleteRoomHouseKeeping(roomHouseKeepingId);
            return new ResponseEntity<>(new APIResponse("success", "RoomHouseKeeping deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (RoomHouseKeepingNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
