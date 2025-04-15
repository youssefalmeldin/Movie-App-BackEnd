package com.fawry.movieapp.controller;

import com.fawry.movieapp.configuration.security.JwtUtil;
import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.LoginDTO;
import com.fawry.movieapp.dto.UserDTO;
import com.fawry.movieapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            // Get the user's role from the authentication object
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            // Generate token with both username and role
            String token = jwtUtil.generateToken(authentication.getName(), role.replace("ROLE_", "")); // ROLE_ADMIN -> ADMIN

            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error", "Invalid credentials"));
        }
    }

    @PostMapping("/register/user")
    public Map<String, String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        return Map.of("token", authService.registerUser(userDTO));
    }

    @PostMapping("/register/admin")
    public Map<String, String> registerAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        return Map.of("token", authService.registerAdmin(adminDTO));
    }
}
