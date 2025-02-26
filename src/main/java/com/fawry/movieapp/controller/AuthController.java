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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok("{ \"token\":\"" + token + "\"}");
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register/user")
    public String registerUser(@RequestBody @Valid UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        return authService.registerAdmin(adminDTO);
    }
}
