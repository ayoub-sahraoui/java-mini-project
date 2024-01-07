package com.hotelbooking.hotelbooking.modules.employee.controller;

import com.hotelbooking.hotelbooking.modules.employee.model.HouseKeeperDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.CreateHouseKeeperDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.UpdateHouseKeeperDTO;
import com.hotelbooking.hotelbooking.modules.employee.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.employee.service.HouseKeeperService;
import com.hotelbooking.hotelbooking.modules.employee.exception.HouseKeeperNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/houseKeepers")
public class HouseKeeperController extends ResponseEntityExceptionHandler {

    @Autowired
    private HouseKeeperService houseKeeperService;

    // Get all houseKeepers
    @GetMapping
    public ResponseEntity<List<HouseKeeperDTO>> getAllHouseKeepers() {
        List<HouseKeeperDTO> houseKeepers = houseKeeperService.getAllHouseKeepers();
        return new ResponseEntity<>(houseKeepers, HttpStatus.OK);
    }

    // Get a specific houseKeeper by ID
    @GetMapping("/{houseKeeperId}")
    public ResponseEntity<Object> getHouseKeeperById(@PathVariable Long houseKeeperId) {
        try {
            HouseKeeperDTO houseKeeper = houseKeeperService.getHouseKeeperById(houseKeeperId);
            return new ResponseEntity<>(new APIResponse("success", "HouseKeeper retrieved successfully", houseKeeper), HttpStatus.OK);
        } catch (HouseKeeperNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new houseKeeper
    @PostMapping
    public ResponseEntity<Object> createHouseKeeper(@RequestBody CreateHouseKeeperDTO houseKeeper) {
        try {
            HouseKeeperDTO createdHouseKeeper = houseKeeperService.createHouseKeeper(houseKeeper);
            return new ResponseEntity<>(new APIResponse("success", "HouseKeeper created successfully", createdHouseKeeper), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing houseKeeper
    @PutMapping("/{houseKeeperId}")
    public ResponseEntity<Object> updateHouseKeeper(@PathVariable Long houseKeeperId, @RequestBody UpdateHouseKeeperDTO houseKeeper) {
        try {
            HouseKeeperDTO updatedHouseKeeper = houseKeeperService.updateHouseKeeper(houseKeeperId, houseKeeper);
            return new ResponseEntity<>(new APIResponse("success", "HouseKeeper updated successfully", updatedHouseKeeper), HttpStatus.OK);
        } catch (HouseKeeperNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a houseKeeper by ID
    @DeleteMapping("/{houseKeeperId}")
    public ResponseEntity<Object> deleteHouseKeeper(@PathVariable Long houseKeeperId) {
        try {
            houseKeeperService.deleteHouseKeeper(houseKeeperId);
            return new ResponseEntity<>(new APIResponse("success", "HouseKeeper deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (HouseKeeperNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
