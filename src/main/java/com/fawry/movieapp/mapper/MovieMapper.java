package com.fawry.movieapp.mapper;

import com.fawry.movieapp.dal.model.Movie;
import com.fawry.movieapp.dto.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO toDTO(Movie movie);

    Movie toEntity(MovieDTO movieDTO);
}
