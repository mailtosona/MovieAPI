package com.ssp.MovieAPI.error;

public class NoRecommendationsFoundException extends Exception {

    public NoRecommendationsFoundException(String s) {
        super(s);
    }
}
