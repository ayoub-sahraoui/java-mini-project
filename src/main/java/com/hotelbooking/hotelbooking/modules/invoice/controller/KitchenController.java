package com.hotelbooking.hotelbooking.modules.invoice.controller;

import com.hotelbooking.hotelbooking.modules.invoice.model.KitchenDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.CreateKitchenDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.UpdateKitchenDTO;
import com.hotelbooking.hotelbooking.modules.invoice.model.APIResponse;
import com.hotelbooking.hotelbooking.modules.invoice.service.KitchenService;
import com.hotelbooking.hotelbooking.modules.invoice.exception.KitchenNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kitchens")
public class KitchenController extends ResponseEntityExceptionHandler {

    @Autowired
    private KitchenService kitchenService;

    // Get all kitchens
    @GetMapping
    public ResponseEntity<List<KitchenDTO>> getAllKitchens() {
        List<KitchenDTO> kitchens = kitchenService.getAllKitchens();
        return new ResponseEntity<>(kitchens, HttpStatus.OK);
    }

    // Get a specific kitchen by ID
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Object> getKitchenById(@PathVariable Long kitchenId) {
        try {
            KitchenDTO kitchen = kitchenService.getKitchenById(kitchenId);
            return new ResponseEntity<>(new APIResponse("success", "Kitchen retrieved successfully", kitchen), HttpStatus.OK);
        } catch (KitchenNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Create a new kitchen
    @PostMapping
    public ResponseEntity<Object> createKitchen(@RequestBody CreateKitchenDTO kitchen) {
        try {
            KitchenDTO createdKitchen = kitchenService.createKitchen(kitchen);
            return new ResponseEntity<>(new APIResponse("success", "Kitchen created successfully", createdKitchen), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Update an existing kitchen
    @PutMapping("/{kitchenId}")
    public ResponseEntity<Object> updateKitchen(@PathVariable Long kitchenId, @RequestBody UpdateKitchenDTO kitchen) {
        try {
            KitchenDTO updatedKitchen = kitchenService.updateKitchen(kitchenId, kitchen);
            return new ResponseEntity<>(new APIResponse("success", "Kitchen updated successfully", updatedKitchen), HttpStatus.OK);
        } catch (KitchenNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }

    // Delete a kitchen by ID
    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Object> deleteKitchen(@PathVariable Long kitchenId) {
        try {
            kitchenService.deleteKitchen(kitchenId);
            return new ResponseEntity<>(new APIResponse("success", "Kitchen deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (KitchenNotFoundException ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new APIResponse("error", ex.getMessage(), null), HttpStatus.FORBIDDEN);
        }
    }
}
