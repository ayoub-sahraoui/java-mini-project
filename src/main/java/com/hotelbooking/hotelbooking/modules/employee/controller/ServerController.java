package com.hotelbooking.hotelbooking.modules.employee.controller;

import com.hotelbooking.hotelbooking.modules.employee.model.ServerDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateServerDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateServerDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.employee.service.ServerService;
import com.hotelbooking.hotelbooking.modules.employee.exception.ServerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servers")
public class ServerController extends ResponseEntityExceptionHandler {

    @Autowired
    private ServerService serverService;

    // Get all servers
    @GetMapping
    public ResponseEntity<List<ServerDTO>> getAllServers() {
        List<ServerDTO> servers = serverService.getAllServers();
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    // Get a specific server by ID
    @GetMapping("/{serverId}")
    public ResponseEntity<Object> getServerById(@PathVariable Long serverId) {
        try {
            ServerDTO server = serverService.getServerById(serverId);
            return new ResponseEntity<>(new APIResponse("success", "Server retrieved successfully", server), HttpStatus.OK);
        } catch (ServerNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new server
    @PostMapping
    public ResponseEntity<Object> createServer(@RequestBody CreateServerDTO server) {
        try {
            ServerDTO createdServer = serverService.createServer(server);
            return new ResponseEntity<>(new APIResponse("success", "Server created successfully", createdServer), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing server
    @PutMapping("/{serverId}")
    public ResponseEntity<Object> updateServer(@PathVariable Long serverId, @RequestBody UpdateServerDTO server) {
        try {
            ServerDTO updatedServer = serverService.updateServer(serverId, server);
            return new ResponseEntity<>(new APIResponse("success", "Server updated successfully", updatedServer), HttpStatus.OK);
        } catch (ServerNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a server by ID
    @DeleteMapping("/{serverId}")
    public ResponseEntity<Object> deleteServer(@PathVariable Long serverId) {
        try {
            serverService.deleteServer(serverId);
            return new ResponseEntity<>(new APIResponse("success", "Server deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (ServerNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
