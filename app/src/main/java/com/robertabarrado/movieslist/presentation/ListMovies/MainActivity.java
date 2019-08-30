package com.robertabarrado.movieslist.presentation.ListMovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.robertabarrado.data.MoviePersistence;
import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.data.Movies_API;
import com.robertabarrado.domain.Movie;
import com.robertabarrado.movieslist.R;
import com.robertabarrado.movieslist.framework.ApiRest;
import com.robertabarrado.movieslist.framework.FakePersistence;
import com.robertabarrado.usescases.GetMovieDetails;
import com.robertabarrado.usescases.GetPopularMovies;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.ViewInterface {


    private Contract.PresenterInterface mPresenter;
    private Movies_API mMovies_api;
    private MoviePersistence mMoviePersistence;
    private MoviesRepository mMoviesRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mPresenter.loadMoreMovies();
    }


    private void init() {

        mMovies_api = new ApiRest();
        mMoviePersistence = new FakePersistence();
        mMoviesRepository = new MoviesRepository(mMoviePersistence, mMovies_api);

        mPresenter = new MainActivityPresenter(this, new GetMovieDetails(mMoviesRepository), new GetPopularMovies(mMoviesRepository));
    }

    @Override
    public void renderMovies(List<Movie> movies) {

        for (Movie m: movies) {

            Log.d("MainActivity", "Movie " + m.getTitle() );
        }

    }

}
