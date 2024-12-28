package com.example.aui_director_management.movie.repository.api;

import com.example.aui_director_management.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Optional<byte[]> findPosterById(UUID id);
}
