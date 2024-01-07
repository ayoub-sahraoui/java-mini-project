package com.hotelbooking.hotelbooking.modules.room.controller;

import com.hotelbooking.hotelbooking.modules.room.model.RoomDTO;
import com.hotelbooking.hotelbooking.modules.room.model.CreateRoomDTO;
import com.hotelbooking.hotelbooking.modules.room.model.UpdateRoomDTO;
import com.hotelbooking.hotelbooking.modules.room.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.room.service.RoomService;
import com.hotelbooking.hotelbooking.modules.room.exception.RoomNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController extends ResponseEntityExceptionHandler {

    @Autowired
    private RoomService roomService;

    // Get all rooms
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Get a specific room by ID
    @GetMapping("/{roomId}")
    public ResponseEntity<Object> getRoomById(@PathVariable Long roomId) {
        try {
            RoomDTO room = roomService.getRoomById(roomId);
            return new ResponseEntity<>(new APIResponse("success", "Room retrieved successfully", room), HttpStatus.OK);
        } catch (RoomNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new room
    @PostMapping
    public ResponseEntity<Object> createRoom(@RequestBody CreateRoomDTO room) {
        try {
            RoomDTO createdRoom = roomService.createRoom(room);
            return new ResponseEntity<>(new APIResponse("success", "Room created successfully", createdRoom), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing room
    @PutMapping("/{roomId}")
    public ResponseEntity<Object> updateRoom(@PathVariable Long roomId, @RequestBody UpdateRoomDTO room) {
        try {
            RoomDTO updatedRoom = roomService.updateRoom(roomId, room);
            return new ResponseEntity<>(new APIResponse("success", "Room updated successfully", updatedRoom), HttpStatus.OK);
        } catch (RoomNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a room by ID
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Object> deleteRoom(@PathVariable Long roomId) {
        try {
            roomService.deleteRoom(roomId);
            return new ResponseEntity<>(new APIResponse("success", "Room deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (RoomNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
