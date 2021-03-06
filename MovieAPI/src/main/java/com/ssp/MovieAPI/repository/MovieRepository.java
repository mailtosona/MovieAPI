package com.ssp.MovieAPI.repository;

import com.ssp.MovieAPI.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.net.ContentHandler;
import java.util.List;

@Repository
public interface MovieRepository  extends   JpaRepository<Movie,Long> {

    @Query("select s from Movie s where s.averageRating= ?1")
    List<Movie> getMovieByAverageRating(double averageRating);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Movie s WHERE s.releaseYear = :year " +
                    "AND averageRating >= :minimumRating " +
                    "AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByReleaseYear(@Param("year") int year, double minimumRating, int minimumVotes);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Movie s WHERE s.releaseYear >= :startYear AND s.releaseYear <= :endYear" +
                    " AND averageRating >= :minimumRating" +
                    " AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes);
}
