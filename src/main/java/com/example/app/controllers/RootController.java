package com.example.app.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping({"/", "/health"})
    public Map<String, String> root() {
        return Map.of(
            "message", "Backend API is running",
            "docs", "Use /api/login, /api/signup, /api/verifyotp, /api/profile"
        );
    }
}
