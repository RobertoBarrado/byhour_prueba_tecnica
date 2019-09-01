package com.robertabarrado.movieslist.presentation.MovieDetails;


import android.util.Log;

import com.robertabarrado.domain.Movie;
import com.robertabarrado.usescases.GetSimilarMovies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MovieDetailsPresenter implements Contract.PresenterInterface {

    private Contract.ViewInterface mView;
    private GetSimilarMovies mGetSimilarMovies;


    MovieDetailsPresenter(Contract.ViewInterface view, GetSimilarMovies getSimilarMovies) {

        mView = view;
        mGetSimilarMovies= getSimilarMovies;
    }


    @Override
    public void getSimilarMovies(int id) {

        mView.showProgressBar();

        // Get MoviesList;
        Observable<Movie> newPaggedMovies = mGetSimilarMovies.invoke(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        DisposableObserver<Movie> disposableObserver = newPaggedMovies.subscribeWith(new
            DisposableObserver<Movie>() {

             private List<Movie> newmovies = new ArrayList<>();

             @Override
             public void onNext(Movie newMovie) {
                 newmovies.add(newMovie);
             }

             @Override
             public void onError(Throwable e) {
                 mView.hideProgressBar();
             }

             @Override
             public void onComplete() {
                 mView.showSimilar(newmovies);
                 mView.hideProgressBar();
             }
        });

    }

    @Override
    public void onMovieClicked(Movie movie) {
        Log.d("ROBERTO", "onMovieClicked");
        mView.openMovieDetails(movie);
    }





}
