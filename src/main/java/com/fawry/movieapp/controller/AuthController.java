package com.fawry.movieapp.controller;

import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.UserDTO;
import com.fawry.movieapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/user")
    public String registerUser(@RequestBody @Valid UserDTO userDTO) {
        return authService.registerUser(userDTO);
    }

    @PostMapping("/admin")
    public String registerAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        return authService.registerAdmin(adminDTO);
    }
}
