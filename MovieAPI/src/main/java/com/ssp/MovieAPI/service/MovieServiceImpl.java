package com.ssp.MovieAPI.service;

import com.ssp.MovieAPI.error.NoRecommendationsFoundException;
import com.ssp.MovieAPI.entity.Movie;
import com.ssp.MovieAPI.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> fetchMoviesListByRatingSorted(int page) throws NoRecommendationsFoundException {
        Pageable sortByRating= PageRequest.of(page,3, Sort.by("averageRating"));
        List<Movie> movie =
                movieRepository.findAll(sortByRating).getContent();
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("No recommendations found.");
        }
        return  movie;
    }

    @Override
    public List<Movie> fetchMoviesListByAverageRating(int rating) throws NoRecommendationsFoundException {
        List<Movie> movie =movieRepository.getMovieByAverageRating(rating);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("No recommendations found.");
        }
        return movie;
    }

    @Override
    public List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException {
        List<Movie> movie = movieRepository.findByReleaseYear(year, minimumRating, minimumVotes);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("No recommendations found.");
        }
        return movie;
    }

    @Override
    public List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimum_rating, int minimum_votes) throws NoRecommendationsFoundException {
        return null;
    }

    @Override
    public List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException {
        List<Movie> movie=movieRepository.findByReleaseYearBetween(startYear, endYear, minimumRating, minimumVotes);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("No recommendations found.");
        }
        return movie;
    }
}
