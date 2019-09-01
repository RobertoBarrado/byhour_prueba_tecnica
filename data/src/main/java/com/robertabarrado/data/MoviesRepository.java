package com.robertabarrado.data;

import com.robertabarrado.domain.Movie;

import java.util.List;

public class MoviesRepository {

    private MoviePersistence mMoviePersistence;
    private Movies_API mMovies_api;

    public MoviesRepository ( MoviePersistence persistence, Movies_API api) {
        mMoviePersistence = persistence;
        mMovies_api = api;
    }



    public Movie getMovieDetails(int id) {

        Movie mMovie = mMoviePersistence.getMovieDetails(id);
        if (mMovie == null)  return mMovies_api.getMovieDetails(id);

        return mMovie;
    }


    public List<Movie> getPopularMovies(int page) {

        List<Movie> moviesList = mMoviePersistence.getPopularMovies(page);
        if (moviesList == null || moviesList.isEmpty()) moviesList = mMovies_api.getPopularMovies(page);
        return moviesList;
    }

    public List<Movie> getSimilarMovies(int id) {

        List<Movie> moviesList = mMoviePersistence.getSimilarMovies(id);
        if (moviesList == null || moviesList.isEmpty()) moviesList = mMovies_api.getSimilarMovies(id);
        return moviesList;
    }


}
