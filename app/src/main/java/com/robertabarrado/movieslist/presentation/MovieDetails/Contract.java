package com.robertabarrado.movieslist.presentation.MovieDetails;

import com.robertabarrado.domain.Movie;

import java.util.List;

class Contract {

    interface ViewInterface {
        void showSimilar(List<Movie> movies);
        void showProgressBar();
        void hideProgressBar();
        void openMovieDetails(Movie e);
    }


    interface PresenterInterface {

        void getSimilarMovies(int id);
        void onMovieClicked(Movie movie);

    }

}
