package com.ssp.MovieAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Movie {
    private String movieId;
    private String movieType;

    private String movieName;
    private int releaseYear;
    private int runningTime;
    private String movieGenre;
    private double averageRating;
    private int numberOfVotes;

}
