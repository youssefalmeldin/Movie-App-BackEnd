package com.fawry.movieapp.service;

import com.fawry.movieapp.dto.MovieDTO;

import java.util.List;

public interface ExternalMovieService {
    MovieDTO getMovieByImdbId(String imdbId);
    List<MovieDTO> searchMovies(String searchText, Integer page);
}
