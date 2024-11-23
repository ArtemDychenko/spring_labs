package com.example.aui.controller;

import com.example.aui.Director;
import com.example.aui.Movie;
import com.example.aui.MovieCreateDTO;
import com.example.aui.MovieDTO;
import com.example.aui.service.DirectorService;
import com.example.aui.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;

    @Autowired
    public MovieController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies().stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{genre}")
    public List<MovieDTO> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre).stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/director/{id}")
    public List<MovieDTO> getMoviesByDirector(@PathVariable UUID id) {
        return movieService.getAllMovies().stream()
                .filter(Movie -> Movie.getDirector().getId().equals(id))
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieCreateDTO dto) {
        Director director = directorService.getDirectorById(UUID.fromString(dto.getDirectorId()));
        if (director == null) {
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println(director);

        Movie movie = new Movie.Builder(dto.getName(), dto.getDate_of_release())
                .time(dto.getTime())
                .genre(dto.getGenre())
                .setDirector(director)
                .build();
        Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MovieDTO(savedMovie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable UUID id, @RequestBody MovieCreateDTO dto) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        Director director = directorService.getDirectorById(UUID.fromString(dto.getDirectorId()));
        if (director == null) {
            return ResponseEntity.badRequest().body(null);
        }

        movie.setName(dto.getName());
        movie.setDate_of_release(dto.getDate_of_release());
        movie.setTime(dto.getTime());
        movie.setGenre(dto.getGenre());
        movie.setDirector(director);

        Movie updatedMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok(new MovieDTO(updatedMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
