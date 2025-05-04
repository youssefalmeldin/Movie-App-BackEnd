package com.fawry.movieapp.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthDTO {

    private String token;
    private String error;
}
