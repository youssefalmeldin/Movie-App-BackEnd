package com.fawry.movieapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private String id;

    @NotBlank(message = "User Name may not be null")
    private String username;

    @NotBlank(message = "Email may not be null")
    private String email;

    @NotBlank(message = "Password may not be null")
    private String password;
}