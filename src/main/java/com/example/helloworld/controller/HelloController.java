package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "message", "Hello, World!",
            "status", "UP",
            "info", "Spring Boot Application is running on your system"
        );
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
