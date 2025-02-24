package com.fawry.movieapp.service;

import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.UserDTO;

public interface AuthService {

    String registerUser(UserDTO userDTO);

    String registerAdmin(AdminDTO adminDTO);
}