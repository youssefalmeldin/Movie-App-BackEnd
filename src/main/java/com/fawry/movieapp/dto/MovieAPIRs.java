package com.fawry.movieapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieAPIRs {

    @JsonProperty("Search")
    private List<MovieDTO> search;

    @JsonProperty("totalResults")
    private Integer totalResults;

    @JsonProperty("Response")
    private Boolean response;
}
