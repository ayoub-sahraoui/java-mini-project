package com.hotelbooking.hotelbooking.modules.authentication.controllers;

import com.hotelbooking.hotelbooking.modules.authentication.models.AuthenticationResponse;
import com.hotelbooking.hotelbooking.modules.authentication.services.AuthenticationService;
import com.hotelbooking.hotelbooking.modules.authentication.models.LoginRequest;
import com.hotelbooking.hotelbooking.modules.authentication.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping("/register")
    public ResponseEntity<AuthenticationResponse> register1(){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
