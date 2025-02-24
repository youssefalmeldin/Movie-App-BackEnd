package com.fawry.movieapp.controller;

import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.service.MovieService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/movie")
@PreAuthorize("hasRole('ADMIN')")
@RestController
public class AdminController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> listMovies(@RequestParam @NotBlank(message = "You must provide Search text") String searchText,
                                     @RequestParam(required = false, defaultValue = "1") @Min(1) Integer page) {
        return movieService.listExternalMovies(searchText, page);
    }

    @PostMapping
    public ResponseEntity<Void> addMovie(@RequestParam @NotBlank(message = "You must provide IMDBID") String imdbID) {
        String id = movieService.addMovie(imdbID);
        return ResponseEntity.created(URI.create(id)).build();
    }

    @PostMapping("/all")
    public ResponseEntity<List<MovieDTO>> addMovies(@RequestBody @NotEmpty(message = "You must at least add one IMDBID") List<String> imdbIDs) {
        List<MovieDTO> movieDTOs = movieService.addMovies(imdbIDs);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable @NotBlank(message = "You must get ID") String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
