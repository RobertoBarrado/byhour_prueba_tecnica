package com.robertabarrado.movieslist.presentation.ListMovies;

import android.content.Context;

import com.robertabarrado.domain.Movie;

import java.util.List;

public class Contract {

    interface ViewInterface {
        void renderMovies(List<Movie> movies);
    }


    interface PresenterInterface {

        void loadMoreMovies();
        void openMovieDetails(Context c, Movie movie);


    }

}
