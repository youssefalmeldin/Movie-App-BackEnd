package com.fawry.movieapp.service;

import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.AuthDTO;
import com.fawry.movieapp.dto.LoginDTO;
import com.fawry.movieapp.dto.UserDTO;

public interface AuthService {

    AuthDTO login(LoginDTO loginRequest);

    AuthDTO registerUser(UserDTO userDTO);

    AuthDTO registerAdmin(AdminDTO adminDTO);
}