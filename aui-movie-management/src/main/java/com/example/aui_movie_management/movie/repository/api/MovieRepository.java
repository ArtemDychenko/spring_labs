package com.example.aui_movie_management.movie.repository.api;

import com.example.aui_movie_management.director.entity.Director;
import com.example.aui_movie_management.movie.dto.GetMovieResponse;
import com.example.aui_movie_management.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    List<Movie> findByGenre(String genre);

    List<Movie> findAllByDirector(Director director);

    Optional<byte[]> findPosterById(UUID id);
}
