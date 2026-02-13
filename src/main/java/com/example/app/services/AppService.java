package com.example.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.entities.Otp;
import com.example.app.controllers.AppController;
import com.example.app.entities.*;
import com.example.app.repositories.AppRepo;
import com.example.app.repositories.OtpRepo;
import com.example.app.repositories.RegisterRepo;
import com.example.app.utility.JwtUtil;

@Service
public class AppService {
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
    private final AppRepo repo;
    private final OtpRepo otpRepo;
    private final RegisterRepo registerRepo;
    private final JavaMailSender mailSender;

    public AppService(AppRepo repo,
            OtpRepo otpRepo,
            RegisterRepo registerRepo,
            JavaMailSender mailSender,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
this.repo = repo;
this.otpRepo = otpRepo;
this.registerRepo = registerRepo;
this.mailSender = mailSender;
this.passwordEncoder = passwordEncoder;
this.jwtUtil = jwtUtil;
}

    // ================= CHECK USER EXISTS =================
    public User findUserByUsername(String email) {
        return repo.findByEmail(email);
    }

    // ================= LOGIN PASSWORD CHECK =================
    public String checkLogin(String email, String password) {

    	User user = repo.findByEmail(email);

        if (user == null)
            return "REGISTER";

        if (!user.getPassword().equals(password))
            return "INVALID";

        userSignIn(email, password);
        return "OTP_SENT";
    }

    // ================= SIGNUP =================
    
    public String userSignUp(Register reg) {

        if (registerRepo.existsByEmail(reg.getEmail())) {
        	 return "EMAIL_EXISTS";
        }

        if (registerRepo.existsByMobileNumber(reg.getMobileNumber())) {
        	return "MOBILE_EXISTS";
        }

        Register savedRegister = registerRepo.save(reg);

        User user = new User();
        user.setUsername(reg.getUserName());
        user.setEmail(reg.getEmail());
        user.setPassword(passwordEncoder.encode(reg.getPassword()));
        user.setRegister(savedRegister);

        repo.save(user);

        return "SUCCESS";
    }



    // ================= LOGIN + OTP =================
    public boolean userSignIn(String email, String password) {

    	User user = repo.findByEmail(email);

        if (user == null)
            return false;

        if (!passwordEncoder.matches(password, user.getPassword()))
            return false;


        // Generate 6 digit OTP
        int otp = new Random().nextInt(900000) + 100000;
        System.out.println("OTP: " + otp);

        Otp newOtp = new Otp(otp, LocalDateTime.now(), user);
        otpRepo.save(newOtp);

        sendOtpMail(user.getEmail(), otp);

        return true;
    }

    // ================= SEND EMAIL =================
    private void sendOtpMail(String email, int otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("KODNEST OTP");
        message.setText("Your login OTP is: " + otp);

        mailSender.send(message);
    }

    // ================= VERIFY OTP =================
    public String verifyOtpAndGenerateToken(String email, int otp) {

        Otp ref = otpRepo
            .findTopByUsersEmailAndOtpvalueOrderByCreatedatDesc(email, otp);

        if (ref == null)
            return null;

        User user = ref.getUsers();

        if (ref.getExpiresAt().isBefore(LocalDateTime.now()))
            return null;

        return jwtUtil.generateToken(user.getEmail());
    }


}
