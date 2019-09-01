package com.robertabarrado.movieslist.presentation.ListMovies;

import android.util.Log;

import com.robertabarrado.domain.Movie;
import com.robertabarrado.usescases.GetMovieDetails;
import com.robertabarrado.usescases.GetPopularMovies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter implements Contract.PresenterInterface {

    private Contract.ViewInterface mView;
    private GetPopularMovies mGetPopularMovies;
    private GetMovieDetails mGetMovieDetails;

    private static int page = 0;
    private static boolean loadingMovies = false;


    MainActivityPresenter(Contract.ViewInterface view, GetMovieDetails getMovieDetails, GetPopularMovies getPopularMovies) {

        mView = view;
        mGetMovieDetails = getMovieDetails;
        mGetPopularMovies = getPopularMovies;
    }

    @Override
    public void loadMoreMovies() {
        if (loadingMovies)  return;
        loadingMovies = true;

        mView.showProgressBar();
        Movie m = mGetMovieDetails.invoke(551);
        if (m!=null)    Log.d("MainActivityPresenter" , "Movie title:" + m.getTitle());

        // Get MoviesList;
        Observable<Movie> newPaggedMovies = mGetPopularMovies.invoke(++page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        final DisposableObserver<Movie> disposableObserver = newPaggedMovies.subscribeWith(new
        DisposableObserver<Movie>() {

            private List<Movie> newmovies = new ArrayList<>();

            @Override
            public void onNext(Movie newMovie) {
                newmovies.add(newMovie);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressBar();
                loadingMovies =false;
            }

            @Override
            public void onComplete() {
                mView.renderMovies(newmovies);
                mView.hideProgressBar();
                loadingMovies =false;
            }
        });


    }

    @Override
    public void onMovieClicked(Movie movie) {
        mView.openMovieDetails(movie);
    }


}
