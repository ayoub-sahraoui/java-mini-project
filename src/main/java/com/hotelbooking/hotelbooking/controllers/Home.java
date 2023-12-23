package com.hotelbooking.hotelbooking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Home {
    @GetMapping("/")
    String main(){
        return "Hello";
    }
}
