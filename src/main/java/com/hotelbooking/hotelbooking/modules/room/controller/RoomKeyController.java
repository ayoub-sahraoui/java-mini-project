package com.hotelbooking.hotelbooking.modules.room.controller;

import com.hotelbooking.hotelbooking.modules.room.model.RoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.room.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.room.service.RoomKeyService;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomKeyNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomKeys")
public class RoomKeyController extends ResponseEntityExceptionHandler {

    @Autowired
    private RoomKeyService roomKeyService;

    // Get all roomKeys
    @GetMapping
    public ResponseEntity<List<RoomKeyDTO>> getAllRoomKeys() {
        List<RoomKeyDTO> roomKeys = roomKeyService.getAllRoomKeys();
        return new ResponseEntity<>(roomKeys, HttpStatus.OK);
    }

    // Get a specific roomKey by ID
    @GetMapping("/{roomKeyId}")
    public ResponseEntity<Object> getRoomKeyById(@PathVariable Long roomKeyId) {
        try {
            RoomKeyDTO roomKey = roomKeyService.getRoomKeyById(roomKeyId);
            return new ResponseEntity<>(new APIResponse("success", "RoomKey retrieved successfully", roomKey), HttpStatus.OK);
        } catch (RoomKeyNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new roomKey
    @PostMapping
    public ResponseEntity<Object> createRoomKey(@RequestBody CreateRoomKeyDTO roomKey) {
        try {
            RoomKeyDTO createdRoomKey = roomKeyService.createRoomKey(roomKey);
            return new ResponseEntity<>(new APIResponse("success", "RoomKey created successfully", createdRoomKey), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing roomKey
    @PutMapping("/{roomKeyId}")
    public ResponseEntity<Object> updateRoomKey(@PathVariable Long roomKeyId, @RequestBody UpdateRoomKeyDTO roomKey) {
        try {
            RoomKeyDTO updatedRoomKey = roomKeyService.updateRoomKey(roomKeyId, roomKey);
            return new ResponseEntity<>(new APIResponse("success", "RoomKey updated successfully", updatedRoomKey), HttpStatus.OK);
        } catch (RoomKeyNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a roomKey by ID
    @DeleteMapping("/{roomKeyId}")
    public ResponseEntity<Object> deleteRoomKey(@PathVariable Long roomKeyId) {
        try {
            roomKeyService.deleteRoomKey(roomKeyId);
            return new ResponseEntity<>(new APIResponse("success", "RoomKey deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (RoomKeyNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
