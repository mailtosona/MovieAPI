package com.ssp.MovieAPI.repository;

import com.ssp.MovieAPI.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        Movie movie = new Movie("Movie001", "movie", "Test Movie 1",
                2018, 100, "Action", 8.5, 100);
        movieRepository.save(movie);

        movie = new Movie("Movie002", "movie", "Test Movie 2",
                2018, 110, "Thriller", 6.0, 1000);
        movieRepository.save(movie);

        movie = new Movie("Movie003", "movie", "Test Movie 3",
                2018, 120, "Drama", 9.5, 1000);
        movieRepository.save(movie);

        movie = new Movie("Movie004", "movie", "Test Movie 4",
                2018, 900, "Family", 7.5, 100);
        movieRepository.save(movie);

        movie = new Movie("Movie005", "movie", "Test Movie 5",
                2019, 900, "Action", 4.5, 500);
        movieRepository.save(movie);
    }

    @Test
    public void shouldReturnLimitOf3MoviesForSpecifiedYearWithNoZeroValuesForRatingsAndVotes() {
        List<Movie> movies = movieRepository.findByReleaseYear(2018, 0, 0);
        assertThat(movies).hasSize(3);
    }

    @Test
    public void shouldReturn2MoviesForSpecifiedYearWithValueSetForRatings() {
        List<Movie> movies = movieRepository.findByReleaseYear(2018, 8.0, 0);
        assertThat(movies).hasSize(2);
    }

    @Test
    public void shouldReturn1MovieForSpecifiedYearWithValueSetForRatingsAndVotes() {
        List<Movie> movies = movieRepository.findByReleaseYear(2018, 8.0, 1000);
        assertThat(movies).hasSize(1);
    }

    @Test
    public void shouldReturn0MoviesForYearWithNoMovies() {
        List<Movie> movies = movieRepository.findByReleaseYear(3019, 0.0, 0);
        assertThat(movies).hasSize(0);
    }

    @Test
    public void shouldReturn3MoviesFromSameYearWhenStartAndEndYearAreTheSame() {
        int testYear = 2018;
        int resultsExpected = 3;
        List<Movie> movies = movieRepository.findByReleaseYearBetween(testYear, testYear, 0, 0);
        assertThat(movies).hasSize(resultsExpected);
        assertTrue(movies.stream().allMatch(movie -> movie.getReleaseYear() == testYear));
    }

    @Test
    public void shouldReturn3MoviesFromBetweenStartAndEndYear() {
        int startYear = 2018;
        int endYear = 2019;
        int resultsExpected = 3;
        List<Movie> movies = movieRepository.findByReleaseYearBetween(startYear, endYear, 0, 0);
        assertThat(movies).hasSize(resultsExpected);
        assertTrue(movies.stream().allMatch(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear));
    }

    @Test
    public void shouldReturn0MoviesForAStartAndEndRangeWithNoMovies() {
        int startYear = 3018;
        int endYear = 3019;
        int resultsExpected = 0;
        List<Movie> movies = movieRepository.findByReleaseYearBetween(startYear, endYear, 0, 0);
        assertThat(movies).hasSize(resultsExpected);
    }

}