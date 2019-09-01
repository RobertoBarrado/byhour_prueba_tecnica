package com.robertabarrado.movieslist.framework;

import com.robertabarrado.data.MoviePersistence;
import com.robertabarrado.domain.Movie;

import java.util.List;

public class FakePersistence implements MoviePersistence {


    @Override
    public Movie getMovieDetails(int id) {

        return null;

    }

    @Override
    public List<Movie> getPopularMovies(int page) {

        return null;
    }

    @Override
    public List<Movie> getSimilarMovies(int id) {
        return null;
    }
}
