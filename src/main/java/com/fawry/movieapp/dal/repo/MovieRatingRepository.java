package com.fawry.movieapp.dal.repo;

import com.fawry.movieapp.dal.model.MovieRating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRatingRepository extends MongoRepository<MovieRating, String> {

    List<MovieRating> findByMovieId(String movieId);

    MovieRating findByUserIdAndMovieId(String userId, String movieId);

}
