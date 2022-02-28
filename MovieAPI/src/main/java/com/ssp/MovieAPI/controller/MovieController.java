package com.ssp.MovieAPI.controller;

import com.ssp.MovieAPI.entity.Movie;
import com.ssp.MovieAPI.error.NoRecommendationsFoundException;
import com.ssp.MovieAPI.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    private final double MINIMUM_RATING = 8.0;
    private final int MINIMUM_VOTES = 1000;
    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    //GetMapping (method = RequestMethod.GET) to map two request paths onto handler methods
    @GetMapping("/")
    public String Welcome() {
        return "Welcome to movies";
    }

    //  GetMapping of specified year, /movies/year/2019
    @GetMapping("/movies/year/{year}")
    public ResponseEntity fetchMoviesListByYear(@PathVariable("year") int year) throws NoRecommendationsFoundException {
        LOGGER.info("Inside fetchMovieListByYear of MovieController");
        List<Movie> movies = movieService.fetchMoviesListByReleaseYear(year, MINIMUM_RATING, MINIMUM_VOTES);
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }
    //  GetMapping of specified year  /movies/year?startYear=2020&endYear=2022
    @GetMapping("/movies/year")
    public List<Movie> getMoviesByCreatedDate(@RequestParam int startYear, @RequestParam int endYear) throws NoRecommendationsFoundException {
        LOGGER.info("Inside getMoviesByCreatedDate of MovieController");
        return movieService.findByReleaseYearBetween(startYear, endYear, MINIMUM_RATING, MINIMUM_VOTES);
    }

}
