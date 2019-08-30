package com.robertabarrado.usescases;

import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.domain.Movie;

import java.util.List;

public class GetPopularMovies {

    private MoviesRepository mMoviesRepository;

    public GetPopularMovies(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;

    }

    public List<Movie> invoke() {
        return mMoviesRepository.getPopularMovies();
    }
}
