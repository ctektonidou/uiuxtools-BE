package com.example.uiuxtools.service;

import com.example.uiuxtools.config.AuthenticationResponse;
import com.example.uiuxtools.config.JwtUtil;
import com.example.uiuxtools.model.Role;
import com.example.uiuxtools.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository, JwtUtil jwtUtil) {
        this.usersRepository = usersRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Έλεγχος για User - Admin
        var user = usersRepository.findByEmail(email);
        if (user.isPresent()) {
            var userDetails = user.get();
            if (userDetails.getTypeOfUser().equals(Role.USER)) {
                return new org.springframework.security.core.userdetails.User(
                        userDetails.getEmail(),
                        userDetails.getPassword(),
                        List.of(new SimpleGrantedAuthority("USER"))
                );
            } else if (userDetails.getTypeOfUser().equals(Role.ADMIN)) {
                return new org.springframework.security.core.userdetails.User(
                        userDetails.getEmail(),
                        userDetails.getPassword(),
                        List.of(new SimpleGrantedAuthority("ADMIN"))
                );
            }
        }
        throw new UsernameNotFoundException("User not found with Email: " + email);
    }

    public AuthenticationResponse login(String email, String password) throws Exception {
        UserDetails userDetails = loadUserByUsername(email);

        // Ελέγχουμε αν το password αντιστοιχεί
        if (userDetails.getPassword().equals(password)) {
            // Αντιστοιχούμε τον ρόλο του χρήστη
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(authority -> authority.getAuthority())
                    .orElse("ROLE_UNKNOWN");

            // Δημιουργούμε το JWT με τον ρόλο

            String token = jwtUtil.generateToken(email, role);
            return AuthenticationResponse.builder().token(token).message("Welcome").build();
        } else {
            throw new Exception("Invalid credentials");
        }
    }
}