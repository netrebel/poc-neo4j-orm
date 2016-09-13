package com.poc.neo4j.controllers;

import com.poc.neo4j.domain.Movie;
import com.poc.neo4j.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author miguel.reyes on 9/16/16.
 */
@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.find(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/movies")
    public List<Movie> getMovies() {
        return (List<Movie>) movieService.findAll();
    }

    @PostMapping(value = "/movies")
    public Movie saveMovie(@RequestBody Movie movie) {
        Movie orUpdate = movieService.createOrUpdate(movie);
        return orUpdate;
    }

    @DeleteMapping(value = "/movies/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.delete(id);
    }


}
