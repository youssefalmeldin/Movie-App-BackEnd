package com.fawry.movieapp.service.impl;

import com.fawry.movieapp.dto.MovieAPI;
import com.fawry.movieapp.dto.MovieAPIRs;
import com.fawry.movieapp.dto.MovieDTO;
import com.fawry.movieapp.exception.NotFoundException;
import com.fawry.movieapp.mapper.MovieMapper;
import com.fawry.movieapp.service.ExternalMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalMovieServiceImpl implements ExternalMovieService {

    private final WebClient webClient;
    private final MovieMapper movieMapper;

    @Override
    public MovieDTO getMovieByImdbId(String imdbId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("i", imdbId)
                        .build())
                .retrieve()
                .bodyToMono(MovieAPI.class)
                .blockOptional()
                .map(movieMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

    @Override
    public List<MovieDTO> searchMovies(String searchText, Integer page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("s", searchText)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(MovieAPIRs.class)
                .blockOptional()
                .map(MovieAPIRs::getSearch)
                .stream()
                .flatMap(Collection::stream)
                .map(movieMapper::toDTO)
                .toList();
    }
}
