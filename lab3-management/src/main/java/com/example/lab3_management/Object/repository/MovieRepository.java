package com.example.lab3_management.Object.repository;

import com.example.lab3_management.Object.entity.Director;
import com.example.lab3_management.Object.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByGenre(String genre);
    List<Movie> findAllByDirector(Director director);

}
