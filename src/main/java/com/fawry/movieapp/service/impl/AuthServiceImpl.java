package com.fawry.movieapp.service.impl;

import com.fawry.movieapp.configuration.security.JwtUtil;
import com.fawry.movieapp.dal.model.Admin;
import com.fawry.movieapp.dal.model.User;
import com.fawry.movieapp.dal.repo.AdminRepository;
import com.fawry.movieapp.dal.repo.UserRepository;
import com.fawry.movieapp.dto.AdminDTO;
import com.fawry.movieapp.dto.UserDTO;
import com.fawry.movieapp.exception.ConflictDataException;
import com.fawry.movieapp.mapper.AdminMapper;
import com.fawry.movieapp.mapper.UserMapper;
import com.fawry.movieapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private final JwtUtil jwtUtil;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null ||
                adminRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new ConflictDataException("Username already used");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        // Generate token with ROLE_USER
        return jwtUtil.generateToken(user.getUsername(), "USER");
    }

    public String registerAdmin(AdminDTO adminDTO) {
        if (userRepository.findByUsername(adminDTO.getUsername()) != null ||
                adminRepository.findByUsername(adminDTO.getUsername()) != null) {
            throw new ConflictDataException("Username already used");
        }

        Admin admin = adminMapper.toEntity(adminDTO);
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        adminRepository.save(admin);
        // Generate token with ROLE_ADMIN
        return jwtUtil.generateToken(admin.getUsername(), "ADMIN");
    }
}
