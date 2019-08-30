package com.robertabarrado.usescases;

import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.domain.Movie;

public class GetMovieDetails {

    private MoviesRepository mMoviesRepository;

    public GetMovieDetails(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;

    }

    public Movie invoke(int id) {
        return mMoviesRepository.getMovieDetails(id);
    }
}
