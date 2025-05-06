package com.fawry.movieapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatingAPI {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
