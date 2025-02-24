package com.fawry.movieapp.dto;

import lombok.Data;

@Data
public class MovieRatingDTO {
    private String id;

    private String userId;

    private String movieId;

    private Double rating;

}

