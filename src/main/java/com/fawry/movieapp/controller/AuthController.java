package com.fawry.movieapp.controller;

import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.AuthDTO;
import com.fawry.movieapp.dto.LoginDTO;
import com.fawry.movieapp.dto.UserDTO;
import com.fawry.movieapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody @Valid LoginDTO loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthDTO.builder().token("Invalid credentials").build());
        }
    }

    @PostMapping("/register/user")
    public AuthDTO registerUser(@RequestBody @Valid UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/register/admin")
    public AuthDTO registerAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        return authService.registerAdmin(adminDTO);
    }
}
