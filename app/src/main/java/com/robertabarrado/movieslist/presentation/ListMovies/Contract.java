package com.robertabarrado.movieslist.presentation.ListMovies;

import com.robertabarrado.domain.Movie;

import java.util.List;

class Contract {

    interface ViewInterface {
        void renderMovies(List<Movie> movies);
        void showProgressBar();
        void hideProgressBar();
        void openMovieDetails(Movie e);
    }


    interface PresenterInterface {

        void loadMoreMovies();
        void onMovieClicked(Movie movie);

    }

}
