package com.example.uiuxtools.controller;

import com.example.uiuxtools.config.AuthenticationResponse;
import com.example.uiuxtools.model.LoginRequest;
import com.example.uiuxtools.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out successfully.");
    }

    @Autowired
    public AuthController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            AuthenticationResponse token = userDetailsService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (BadCredentialsException e) {
            AuthenticationResponse resp = AuthenticationResponse.builder().token("").message("error").build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


