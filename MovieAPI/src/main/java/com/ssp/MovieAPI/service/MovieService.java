package com.ssp.MovieAPI.service;

import com.ssp.MovieAPI.error.NoRecommendationsFoundException;
import com.ssp.MovieAPI.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> fetchMoviesListByRatingSorted(int page) throws NoRecommendationsFoundException;

    List<Movie> fetchMoviesListByAverageRating(int rating) throws NoRecommendationsFoundException;

    List<Movie> fetchMoviesListByReleaseYear(int year, double minimum_rating, int minimum_votes) throws NoRecommendationsFoundException;

    List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimum_rating, int minimum_votes) throws NoRecommendationsFoundException;

    List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;
}
