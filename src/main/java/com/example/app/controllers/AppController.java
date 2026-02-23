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
@CrossOrigin(origins = {"http://localhost:5173", "https://reactloginapp1.netlify.app"},allowCredentials = "true")

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

        // Validate input
        if (email == null || email.trim().isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Email is required");
            return response;
        }

        if (password == null || password.trim().isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Password is required");
            return response;
        }

        boolean otpSent = service.userSignIn(email.trim(), password);

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

        Map<String, String> response = new HashMap<>();

        // Get values from request
        String email = request.get("email");
        String otpStr = request.get("otp");

        // Validate that email and OTP are provided
        if (email == null || email.trim().isEmpty()) {
            response.put("status", "failed");
            response.put("message", "Email is required");
            return response;
        }

        if (otpStr == null || otpStr.trim().isEmpty()) {
            response.put("status", "failed");
            response.put("message", "OTP is required");
            return response;
        }

        // Parse OTP with error handling
        int otp;
        try {
            otp = Integer.parseInt(otpStr.trim());
        } catch (NumberFormatException e) {
            response.put("status", "failed");
            response.put("message", "Invalid OTP format. OTP must be a number.");
            return response;
        }

        // Verify OTP and generate token
        String token = service.verifyOtpAndGenerateToken(email, otp);

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
    @GetMapping("/profile")
    public String profile() {
        return "This is protected profile data";
    }

}

