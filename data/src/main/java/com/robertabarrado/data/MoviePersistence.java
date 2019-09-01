package com.robertabarrado.data;

import com.robertabarrado.domain.Movie;

import java.util.List;

public interface MoviePersistence {

    Movie getMovieDetails(int id);
    List<Movie> getPopularMovies(int page);
    List<Movie> getSimilarMovies(int id);
}
