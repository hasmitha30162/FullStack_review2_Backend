package com.klu.ArtifyBackend.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.ArtifyBackend.dto.LoginRequest;
import com.klu.ArtifyBackend.dto.SignupRequest;
import com.klu.ArtifyBackend.model.User;
import com.klu.ArtifyBackend.repository.UserRepository;



@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmailService emailService;

    public String signup(SignupRequest req) {

        String role = req.getRole().toUpperCase();

        if (userRepo.existsByEmailAndRole(req.getEmail(), role)) {
            return role + " account already exists for this email";
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(role);
        user.setVerified(false);

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        user.setOtp(otp);

        userRepo.save(user);

        try {
            emailService.sendOtp(user.getEmail(), otp);
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }

        return "OTP sent to email";
    }

    public String verify(String email, String role, String otp) {
        User user = userRepo.findByEmailAndRole(email, role.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Account not found for this role"));

        if (user.getOtp() != null && user.getOtp().equals(otp)) {
            user.setVerified(true);
            user.setOtp(null);
            userRepo.save(user);
            return "Account verified";
        }

        return "Invalid OTP";
    }

    public User login(LoginRequest req) {
        String role = req.getRole().toUpperCase();

        User user = userRepo.findByEmailAndRole(req.getEmail(), role)
                .orElseThrow(() -> new RuntimeException(role + " account not found"));

        if (!user.isVerified()) {
            throw new RuntimeException("Verify email first");
        }

        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
}