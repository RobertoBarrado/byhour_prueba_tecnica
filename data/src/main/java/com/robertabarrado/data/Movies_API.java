package com.robertabarrado.data;

import com.robertabarrado.domain.Movie;

import java.util.List;

public interface Movies_API {

    Movie getMovieDetails(int id);
    List<Movie> getPopularMovies(int page);
    List<Movie> getSimilarMovies(int id);
}
