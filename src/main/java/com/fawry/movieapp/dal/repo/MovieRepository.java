package com.fawry.movieapp.dal.repo;


import com.fawry.movieapp.dal.model.Movie;
import com.fawry.movieapp.dto.MovieDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findAllByImdbIDIn(Collection<String> imdbIDs);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    boolean existsByImdbID(String imdbID);
}
