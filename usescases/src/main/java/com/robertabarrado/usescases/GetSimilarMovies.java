package com.robertabarrado.usescases;

import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.domain.Movie;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class GetSimilarMovies {

    private MoviesRepository mMoviesRepository;

    public GetSimilarMovies(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;

    }

    public Observable<Movie> invoke(final int id) {

        return Observable.create(new ObservableOnSubscribe<Movie>() {
            @Override
            public void subscribe(ObservableEmitter<Movie> emitter) throws Exception {
                try {
                    List<Movie> newMovies = mMoviesRepository.getSimilarMovies(id);

                    for (Movie m : newMovies)
                        emitter.onNext(m);

                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

    }
}
