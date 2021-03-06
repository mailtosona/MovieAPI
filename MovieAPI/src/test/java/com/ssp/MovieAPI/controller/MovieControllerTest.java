package com.ssp.MovieAPI.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.ssp.MovieAPI.entity.Movie;
import com.ssp.MovieAPI.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest

public class MovieControllerTest {
    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void shouldReturnMovieRecommendationsForTheYear() throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1",2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2",2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3",2018, 100, "Action", 8.5, 1000));

        when(mockMovieServiceImpl.fetchMoviesListByReleaseYear(2018, 8,1000)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/2018"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieName").value("Test Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieName").value("Test Movie 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].movieName").value("Test Movie 3"));
    }
    @Test
    public void shouldReturnMovieRecommendationsForYearBetweenTwoYears() throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1",2016, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2",2017, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3",2018, 100, "Action", 8.5, 1000));

        when(mockMovieServiceImpl.fetchByReleaseYearBetween(2016, 2018,8,1000)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/").param("startYear", "2016").param("endYear", "2018"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(2016))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear").value(2017))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].releaseYear").value(2018));
    }

}