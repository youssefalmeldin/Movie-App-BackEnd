package com.fawry.movieapp.configuration.security;

import com.fawry.movieapp.dal.model.Admin;
import com.fawry.movieapp.dal.model.User;
import com.fawry.movieapp.dal.repo.AdminRepository;
import com.fawry.movieapp.dal.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(), admin.getPassword(), List.of(() -> "ROLE_ADMIN"));
        }

        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), List.of(() -> "ROLE_USER"));
        }

        throw new UsernameNotFoundException("User not found");
    }
}