package com.hotelbooking.hotelbooking.modules.invoice.controller;

import com.hotelbooking.hotelbooking.modules.invoice.model.RoomRelatedDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateRoomRelatedDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateRoomRelatedDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.invoice.service.RoomRelatedService;
import com.hotelbooking.hotelbooking.modules.invoice.exception.RoomRelatedNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomRelateds")
public class RoomRelatedController extends ResponseEntityExceptionHandler {

    @Autowired
    private RoomRelatedService roomRelatedService;

    // Get all roomRelateds
    @GetMapping
    public ResponseEntity<List<RoomRelatedDTO>> getAllRoomRelateds() {
        List<RoomRelatedDTO> roomRelateds = roomRelatedService.getAllRoomRelateds();
        return new ResponseEntity<>(roomRelateds, HttpStatus.OK);
    }

    // Get a specific roomRelated by ID
    @GetMapping("/{roomRelatedId}")
    public ResponseEntity<Object> getRoomRelatedById(@PathVariable Long roomRelatedId) {
        try {
            RoomRelatedDTO roomRelated = roomRelatedService.getRoomRelatedById(roomRelatedId);
            return new ResponseEntity<>(new APIResponse("success", "RoomRelated retrieved successfully", roomRelated), HttpStatus.OK);
        } catch (RoomRelatedNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new roomRelated
    @PostMapping
    public ResponseEntity<Object> createRoomRelated(@RequestBody CreateRoomRelatedDTO roomRelated) {
        try {
            RoomRelatedDTO createdRoomRelated = roomRelatedService.createRoomRelated(roomRelated);
            return new ResponseEntity<>(new APIResponse("success", "RoomRelated created successfully", createdRoomRelated), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing roomRelated
    @PutMapping("/{roomRelatedId}")
    public ResponseEntity<Object> updateRoomRelated(@PathVariable Long roomRelatedId, @RequestBody UpdateRoomRelatedDTO roomRelated) {
        try {
            RoomRelatedDTO updatedRoomRelated = roomRelatedService.updateRoomRelated(roomRelatedId, roomRelated);
            return new ResponseEntity<>(new APIResponse("success", "RoomRelated updated successfully", updatedRoomRelated), HttpStatus.OK);
        } catch (RoomRelatedNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a roomRelated by ID
    @DeleteMapping("/{roomRelatedId}")
    public ResponseEntity<Object> deleteRoomRelated(@PathVariable Long roomRelatedId) {
        try {
            roomRelatedService.deleteRoomRelated(roomRelatedId);
            return new ResponseEntity<>(new APIResponse("success", "RoomRelated deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (RoomRelatedNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
