package com.hotelbooking.hotelbooking.modules.hotel.controller;

import com.hotelbooking.hotelbooking.modules.hotel.model.HotelLocationDTO;
import com.hotelbooking.hotelbooking.modules.hotel.model.CreateHotelLocationDTO;
import com.hotelbooking.hotelbooking.modules.hotel.model.UpdateHotelLocationDTO;
import com.hotelbooking.hotelbooking.modules.hotel.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.hotel.service.HotelLocationService;
import com.hotelbooking.hotelbooking.modules.hotel.exception.HotelLocationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hotel-locations")
public class HotelLocationController extends ResponseEntityExceptionHandler {

    @Autowired
    private HotelLocationService hotelLocationService;

    // Get all hotelLocations
    @GetMapping
    public ResponseEntity<List<HotelLocationDTO>> getAllHotelLocations() {
        List<HotelLocationDTO> hotelLocations = hotelLocationService.getAllHotelLocations();
        return new ResponseEntity<>(hotelLocations, HttpStatus.OK);
    }

    // Get a specific hotelLocation by ID
    @GetMapping("/{hotelLocationId}")
    public ResponseEntity<Object> getHotelLocationById(@PathVariable Long hotelLocationId) {
        try {
            HotelLocationDTO hotelLocation = hotelLocationService.getHotelLocationById(hotelLocationId);
            return new ResponseEntity<>(new APIResponse("success", "HotelLocation retrieved successfully", hotelLocation), HttpStatus.OK);
        } catch (HotelLocationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new hotelLocation
    @PostMapping
    public ResponseEntity<Object> createHotelLocation(@RequestBody CreateHotelLocationDTO hotelLocation) {
        try {
            HotelLocationDTO createdHotelLocation = hotelLocationService.createHotelLocation(hotelLocation);
            return new ResponseEntity<>(new APIResponse("success", "HotelLocation created successfully", createdHotelLocation), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing hotelLocation
    @PutMapping("/{hotelLocationId}")
    public ResponseEntity<Object> updateHotelLocation(@PathVariable Long hotelLocationId, @RequestBody UpdateHotelLocationDTO hotelLocation) {
        try {
            HotelLocationDTO updatedHotelLocation = hotelLocationService.updateHotelLocation(hotelLocationId, hotelLocation);
            return new ResponseEntity<>(new APIResponse("success", "HotelLocation updated successfully", updatedHotelLocation), HttpStatus.OK);
        } catch (HotelLocationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a hotelLocation by ID
    @DeleteMapping("/{hotelLocationId}")
    public ResponseEntity<Object> deleteHotelLocation(@PathVariable Long hotelLocationId) {
        try {
            hotelLocationService.deleteHotelLocation(hotelLocationId);
            return new ResponseEntity<>(new APIResponse("success", "HotelLocation deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (HotelLocationNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
