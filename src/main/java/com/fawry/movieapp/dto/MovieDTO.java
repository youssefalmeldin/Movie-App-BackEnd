package com.fawry.movieapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {

    private String id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Rated")
    private String rated;

    @JsonProperty("Released")
    private String released;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Writer")
    private String writer;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Language")
    private String language;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("Ratings")
    private List<RatingDTO> ratings;

    @JsonProperty("Metascore")
    private String metascore;

    @JsonProperty("imdbRating")
    private String imdbRating;

    @JsonProperty("imdbVotes")
    private String imdbVotes;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("DVD")
    private String dvd;

    @JsonProperty("BoxOffice")
    private String boxOffice;

    @JsonProperty("Production")
    private String production;

    @JsonProperty("Website")
    private String website;

    @JsonProperty("Response")
    private String response;

}
