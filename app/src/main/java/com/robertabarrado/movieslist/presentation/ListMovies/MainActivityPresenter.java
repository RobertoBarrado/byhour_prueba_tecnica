package com.robertabarrado.movieslist.presentation.ListMovies;

import android.content.Context;

import com.robertabarrado.domain.Movie;
import com.robertabarrado.usescases.GetMovieDetails;
import com.robertabarrado.usescases.GetPopularMovies;

import java.util.ArrayList;
import java.util.List;


public class MainActivityPresenter implements Contract.PresenterInterface {

    private Contract.ViewInterface mView;
    private GetPopularMovies mGetPopularMovies;
    private GetMovieDetails mGetMovieDetails;

    private List<Movie> movies;


    MainActivityPresenter(Contract.ViewInterface view, GetMovieDetails getMovieDetails, GetPopularMovies getPopularMovies) {

        mView = view;
        mGetMovieDetails = getMovieDetails;
        mGetPopularMovies = getPopularMovies;
        movies = new ArrayList<>();
    }

    @Override
    public void loadMoreMovies() {


        // Get MoviesList;
        List<Movie> newPaggedMovies = mGetPopularMovies.invoke();

        movies.addAll(newPaggedMovies);

        mView.renderMovies(movies);

    }

    @Override
    public void openMovieDetails(Context c, Movie movie) {
        //c.startActivity();
    }


}
