package com.fawry.movieapp.service;

import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.dto.MovieRatingDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> listExternalMovies(String searchText, Integer page);

    List<MovieDTO> listInternalMovies(String title);

    MovieDTO getMovie(String id);

    String addMovie(String imdbID);

    List<MovieDTO> addMovies(List<String> imdbIDs);

    void deleteMovie(String id);

    void rateMovie(MovieRatingDTO movieRatingDTO);

    double getMovieAverageRating(String movieId);
}
