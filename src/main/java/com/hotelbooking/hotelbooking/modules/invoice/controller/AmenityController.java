package com.hotelbooking.hotelbooking.modules.invoice.controller;

import com.hotelbooking.hotelbooking.modules.invoice.model.AmenityDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateAmenityDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateAmenityDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.invoice.service.AmenityService;
import com.hotelbooking.hotelbooking.modules.invoice.exception.AmenityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController extends ResponseEntityExceptionHandler {

    @Autowired
    private AmenityService amenityService;

    // Get all amenities
    @GetMapping
    public ResponseEntity<List<AmenityDTO>> getAllAmenitys() {
        List<AmenityDTO> amenities = amenityService.getAllAmenities();
        return new ResponseEntity<>(amenities, HttpStatus.OK);
    }

    // Get a specific amenity by ID
    @GetMapping("/{amenityId}")
    public ResponseEntity<Object> getAmenityById(@PathVariable Long amenityId) {
        try {
            AmenityDTO amenity = amenityService.getAmenityById(amenityId);
            return new ResponseEntity<>(new APIResponse("success", "Amenity retrieved successfully", amenity), HttpStatus.OK);
        } catch (AmenityNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new amenity
    @PostMapping
    public ResponseEntity<Object> createAmenity(@RequestBody CreateAmenityDTO amenity) {
        try {
            AmenityDTO createdAmenity = amenityService.createAmenity(amenity);
            return new ResponseEntity<>(new APIResponse("success", "Amenity created successfully", createdAmenity), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing amenity
    @PutMapping("/{amenityId}")
    public ResponseEntity<Object> updateAmenity(@PathVariable Long amenityId, @RequestBody UpdateAmenityDTO amenity) {
        try {
            AmenityDTO updatedAmenity = amenityService.updateAmenity(amenityId, amenity);
            return new ResponseEntity<>(new APIResponse("success", "Amenity updated successfully", updatedAmenity), HttpStatus.OK);
        } catch (AmenityNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a amenity by ID
    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Object> deleteAmenity(@PathVariable Long amenityId) {
        try {
            amenityService.deleteAmenity(amenityId);
            return new ResponseEntity<>(new APIResponse("success", "Amenity deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (AmenityNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
