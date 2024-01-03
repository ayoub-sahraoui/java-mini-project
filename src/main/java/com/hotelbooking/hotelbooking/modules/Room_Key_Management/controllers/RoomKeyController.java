package com.hotelbooking.hotelbooking.modules.Room_Key_Management.controllers;

import com.hotelbooking.hotelbooking.modules.Room_Key_Management.DTO.RoomKeyDTO;
import com.hotelbooking.hotelbooking.modules.Room_Key_Management.services.RoomKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-keys")
public class RoomKeyController {

    private final RoomKeyService roomKeyService;

    @Autowired
    public RoomKeyController(RoomKeyService roomKeyService) {
        this.roomKeyService = roomKeyService;
    }

    @GetMapping
    public ResponseEntity<List<RoomKeyDTO>> getAllRoomKeys() {
        List<RoomKeyDTO> roomKeys = roomKeyService.getAllRoomKeys();
        return ResponseEntity.ok(roomKeys);
    }

    @GetMapping("/RoomKey/{id}")
    public ResponseEntity<RoomKeyDTO> getRoomKeyById(@PathVariable int id) {
        RoomKeyDTO roomKeyDTO = roomKeyService.getRoomKeyById(id);
        return ResponseEntity.ok(roomKeyDTO);
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<RoomKeyDTO> createRoomKey(@RequestBody RoomKeyDTO roomKeyDTO, @PathVariable Long roomId) {
        RoomKeyDTO createdRoomKey = roomKeyService.createRoomKey(roomKeyDTO, roomId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomKey);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomKeyDTO> updateRoomKey(@PathVariable int id, @RequestBody RoomKeyDTO roomKeyDTO) {
        RoomKeyDTO updatedRoomKey = roomKeyService.updateRoomKey(id, roomKeyDTO);
        return ResponseEntity.ok(updatedRoomKey);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoomKey(@PathVariable int id) {
        roomKeyService.deleteRoomKey(id);
        return ResponseEntity.ok().build();
    }
}
