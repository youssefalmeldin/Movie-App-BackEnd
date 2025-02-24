package com.fawry.movieapp.dal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MovieRating {

    @Id
    private String id;

    private String userId;

    private String movieId;

    private Double rating;
}
