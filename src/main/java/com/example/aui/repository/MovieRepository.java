package com.example.aui.repository;

import com.example.aui.Director;
import com.example.aui.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByGenre(String genre);
}
