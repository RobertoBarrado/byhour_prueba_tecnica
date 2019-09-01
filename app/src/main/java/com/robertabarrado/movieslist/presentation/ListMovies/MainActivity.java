package com.robertabarrado.movieslist.presentation.ListMovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.robertabarrado.data.MoviePersistence;
import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.data.Movies_API;
import com.robertabarrado.domain.Movie;
import com.robertabarrado.movieslist.framework.ApiRest;
import com.robertabarrado.movieslist.framework.FakePersistence;
import com.robertabarrado.movieslist.presentation.MovieDetails.MovieDetails;
import com.robertabarrado.usescases.GetMovieDetails;
import com.robertabarrado.usescases.GetPopularMovies;
import com.robertobarrado.movieslist.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.ViewInterface {


    private Contract.PresenterInterface mPresenter;

    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initRecyclerView();


        mPresenter.loadMoreMovies();
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, listener));

        mAdapter = new MyAdapter(listener);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    mPresenter.loadMoreMovies();
                }
            }
        });




    }

    private void init() {

        Movies_API mMovies_api = new ApiRest();
        MoviePersistence mMoviePersistence = new FakePersistence();
        MoviesRepository mMoviesRepository = new MoviesRepository(mMoviePersistence, mMovies_api);

        mPresenter = new MainActivityPresenter(this, new GetMovieDetails(mMoviesRepository), new GetPopularMovies(mMoviesRepository));
    }

    @Override
    public void renderMovies(List<Movie> movies) {

        Log.d("MainActivity" , "renderMovies: " + movies.size());
        mAdapter.refresh(movies);

    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void openMovieDetails(Movie movie) {
        Intent i = new Intent(this, MovieDetails.class);
        i.putExtra("movie", movie);

        startActivity(i);
    }


    RecyclerItemClickListener.OnItemClickListener listener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override public void onItemClick(View view, int position) {

            Movie movie = mAdapter.getItem(position);
            mPresenter.onMovieClicked(movie);

        }

        @Override public void onLongItemClick(View view, int position) {

        }
    };


}














