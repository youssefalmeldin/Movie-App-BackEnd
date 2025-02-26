package com.fawry.movieapp.service.impl;


import com.fawry.movieapp.dal.model.Movie;
import com.fawry.movieapp.dal.model.MovieRating;
import com.fawry.movieapp.dal.repo.MovieRatingRepository;
import com.fawry.movieapp.dal.repo.MovieRepository;
import com.fawry.movieapp.dto.MovieAPIRs;
import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.dto.MovieRatingDTO;
import com.fawry.movieapp.exception.ConflictDataException;
import com.fawry.movieapp.exception.NotFoundException;
import com.fawry.movieapp.mapper.MovieMapper;
import com.fawry.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final WebClient webClient;

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    private final MovieRatingRepository movieRatingRepository;

    @Override
    public List<MovieDTO> listExternalMovies(String searchText, Integer page) {
        List<MovieDTO> apiMovies = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("s", searchText)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(MovieAPIRs.class)
                .blockOptional()
                .map(MovieAPIRs::getSearch)
                .orElse(Collections.emptyList());

        Set<String> imdbIds = apiMovies.stream().map(MovieDTO::getImdbID).collect(Collectors.toSet());
        Map<String, String> moviesIds = movieRepository.findAllByImdbIDIn(imdbIds)
                .stream().collect(Collectors.toMap(Movie::getImdbID, Movie::getId));

        for (MovieDTO movieDTO : apiMovies) {
            String imdbID = movieDTO.getImdbID();
            String dbId = moviesIds.get(imdbID);
            movieDTO.setId(dbId);
        }
        return apiMovies;
    }

    @Override
    public List<MovieDTO> listInternalMovies(String nullableTitle) {
        List<Movie> movies = Optional.ofNullable(nullableTitle)
                .map(movieRepository::findByTitleContainingIgnoreCase)
                .orElse(movieRepository.findAll());

        return movies.stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String addMovie(String imdbID) {
        // TODO: Check if imdbID exist in DB or not
        MovieDTO movieDTO = getMovieByImdbId(imdbID);
        if (movieRepository.existsByImdbID(imdbID)) {
            throw new ConflictDataException("Movie already exists");
        }
        Movie movie = movieMapper.toEntity(movieDTO);
        return movieRepository.save(movie).getId();
    }

    @Override
    public List<MovieDTO> addMovies(List<String> imdbIDs) {
        List<Movie> movies = imdbIDs.stream()
                .map(this::getMovieByImdbId)
                .map(movieMapper::toEntity)
                .collect(Collectors.toList());

        return movieRepository.saveAll(movies)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteMovie(String id) {

        movieRepository.deleteById(id);
    }

    @Override
    public void rateMovie(MovieRatingDTO movieRatingDTO) {
        Movie movie = movieRepository.findById(movieRatingDTO.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        MovieRating rating = movieRatingRepository.findByUserIdAndMovieId(movieRatingDTO.getUserId(), movieRatingDTO.getMovieId());
        if (rating == null) {
            rating = new MovieRating();
            rating.setMovieId(movie.getId());
            rating.setUserId(movieRatingDTO.getUserId());
        }
        rating.setRating(movieRatingDTO.getRating());
        movieRatingRepository.save(rating);
    }


    @Override
    public MovieDTO getMovie(String id) {
        return movieRepository.findById(id)
                .map(movieMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }


    @Override
    public double getMovieAverageRating(String movieId) {
        List<MovieRating> ratings = movieRatingRepository.findByMovieId(movieId);
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToDouble(MovieRating::getRating).average().orElse(0);
    }
    private MovieDTO getMovieByImdbId(String imdbID) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("i", imdbID)
                        .build())
                .retrieve()
                .bodyToMono(MovieDTO.class)
                .blockOptional()
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }
}
