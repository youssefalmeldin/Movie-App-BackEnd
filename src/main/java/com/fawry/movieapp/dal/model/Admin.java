package com.fawry.movieapp.dal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;
}