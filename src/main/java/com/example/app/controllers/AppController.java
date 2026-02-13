package com.example.app.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entities.Register;
import com.example.app.entities.User;
import com.example.app.services.AppService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // allow React dev server

public class AppController {

    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    // ================= LOGIN (PASSWORD CHECK + SEND OTP) =================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        Map<String, String> response = new HashMap<>();

        String email = request.get("email");
        String password = request.get("password");

        boolean otpSent = service.userSignIn(email, password);

        if (otpSent) {
            response.put("status", "otp_sent");
            response.put("message", "OTP sent to registered email");
        } else {
            response.put("status", "failed");
            response.put("message", "Invalid username or password OR user not registered");
        }

        return response;
    }

    // ================= VERIFY OTP =================
    @PostMapping("/verifyotp")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        int otp = Integer.parseInt(request.get("otp"));

        String token = service.verifyOtpAndGenerateToken(email, otp);

        Map<String, String> response = new HashMap<>();

        if (token == null) {
            response.put("status", "failed");
            response.put("message", "Invalid OTP");
        } else {
            response.put("status", "success");
            response.put("token", token);
        }

        return response;
    }

    // ================= SIGNUP =================
    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Register newUser) {

        Map<String, String> response = new HashMap<>();

        String result = service.userSignUp(newUser);

        if (result.equals("SUCCESS")) {
            response.put("status", "success");
            response.put("message", "User registered. Please login.");
        }
        else if (result.equals("EMAIL_EXISTS")) {
            response.put("status", "failed");
            response.put("message", "Email already registered");
        }
        else if (result.equals("MOBILE_EXISTS")) {
            response.put("status", "failed");
            response.put("message", "Mobile number already registered");
        }
        else {
            response.put("status", "failed");
            response.put("message", "Signup failed");
        }

        return response;
    }
    @GetMapping("/api/profile")
    public String profile() {
        return "This is protected profile data";
    }

}

