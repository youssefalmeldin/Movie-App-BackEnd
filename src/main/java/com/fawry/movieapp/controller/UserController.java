package com.fawry.movieapp.controller;


import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.service.MovieService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users/movie")
@PreAuthorize("hasRole('USER')")
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

}
