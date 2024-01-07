package com.hotelbooking.hotelbooking.modules.hotel.controller;

import com.hotelbooking.hotelbooking.modules.hotel.model.*;
import com.hotelbooking.hotelbooking.modules.hotel.service.HotelService;
import com.hotelbooking.hotelbooking.modules.hotel.exception.HotelNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController extends ResponseEntityExceptionHandler {

    @Autowired
    private HotelService hotelService;

    // Get all hotels
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    // Get a specific hotel by ID
    @GetMapping("/{hotelId}")
    public ResponseEntity<Object> getHotelById(@PathVariable Long hotelId) {
        try {
            HotelDTO hotel = hotelService.getHotelById(hotelId);
            return new ResponseEntity<>(new APIResponse("success", "Hotel retrieved successfully", hotel), HttpStatus.OK);
        } catch (HotelNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new hotel
    @PostMapping
    public ResponseEntity<Object> createHotel(@RequestBody CreateHotelDTO hotel) {
        try {
            HotelDTO createdHotel = hotelService.createHotel(hotel);
            return new ResponseEntity<>(new APIResponse("success", "Hotel created successfully", createdHotel), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing hotel
    @PutMapping("/{hotelId}")
    public ResponseEntity<Object> updateHotel(@PathVariable Long hotelId, @RequestBody UpdateHotelDTO hotel) {
        try {
            HotelDTO updatedHotel = hotelService.updateHotel(hotelId, hotel);
            return new ResponseEntity<>(new APIResponse("success", "Hotel updated successfully", updatedHotel), HttpStatus.OK);
        } catch (HotelNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a hotel by ID
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Object> deleteHotel(@PathVariable Long hotelId) {
        try {
            hotelService.deleteHotel(hotelId);
            return new ResponseEntity<>(new APIResponse("success", "Hotel deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (HotelNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("{hotelId}/locations")
    public ResponseEntity<List<HotelLocationDTO>> getHotelLocations(@PathVariable Long hotelId) {
        List<HotelLocationDTO> hotelsLocations = hotelService.getAllHotelLocations(hotelId);
        return new ResponseEntity<>(hotelsLocations, HttpStatus.OK);
    }
}
