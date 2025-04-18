package com.fawry.movieapp.controller;

import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.dto.MovieRatingDTO;
import com.fawry.movieapp.service.MovieService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users/movie")
@RestController
public class UserController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> listMovies(@RequestParam(required = false) String title) {
        return movieService.listInternalMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovie(@PathVariable @NotBlank(message = "You must get ID") String id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/{movieId}/average-rating")
    public ResponseEntity<Double> getMovieAverageRating(@PathVariable String movieId) {
        double averageRating = movieService.getMovieAverageRating(movieId);
        return ResponseEntity.ok(averageRating);
    }

    @PostMapping("/rate")
    public ResponseEntity<Void> rateMovie(@RequestBody MovieRatingDTO movieRatingDTO) {
        movieService.rateMovie(movieRatingDTO);
        return ResponseEntity.ok().build();
    }

}
