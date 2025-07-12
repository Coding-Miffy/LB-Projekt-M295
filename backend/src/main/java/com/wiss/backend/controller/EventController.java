package com.wiss.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @GetMapping("/hello")  // ← HTTP GET auf /hello
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/")  // ← HTTP GET auf /
    public String home() {
        return "Quiz Backend is running! 🚀";
    }
}
