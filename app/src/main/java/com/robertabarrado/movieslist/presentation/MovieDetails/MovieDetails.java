package com.robertabarrado.movieslist.presentation.MovieDetails;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.robertabarrado.data.MoviesRepository;
import com.robertabarrado.domain.Movie;
import com.robertabarrado.movieslist.framework.ApiRest;
import com.robertabarrado.movieslist.framework.FakePersistence;
import com.robertabarrado.usescases.GetSimilarMovies;
import com.robertobarrado.movieslist.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MovieDetails extends AppCompatActivity implements Contract.ViewInterface {

    private MovieDetailsPresenter mPresenter;
    private LinearLayout llSimilarMovies;
    private ProgressBar similarMoviesLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie movie = null;
        if (getIntent().hasExtra("movie")) {
             movie = (Movie) Objects.requireNonNull(getIntent().getExtras()).get("movie");
        }
        else finish();

        ImageView backdrop = findViewById(R.id.backdrop_image);
        ImageView poster = findViewById(R.id.movide_details_poster);
        TextView title = findViewById(R.id.movie_details_title);
        TextView overview = findViewById(R.id.movide_details_overview);
        TextView popularity = findViewById(R.id.movie_details_popularidad);
        TextView video_count = findViewById(R.id.movie_details_video_count);
        TextView release_data = findViewById(R.id.movie_details_release_data);
        RatingBar vote_average = findViewById(R.id.movie_details_ratingBar);


        llSimilarMovies = findViewById(R.id.ll_similar_movies);
        similarMoviesLoading = findViewById(R.id.progress_circular);



        vote_average.setRating((float) Objects.requireNonNull(movie).getVote_average()/2);
        release_data.setText(String.format(getString(R.string.release_date), movie.getRelease_date()));
        popularity.setText(String.format(getString(R.string.popularity), movie.getPopularity()+""));
        video_count.setText(String.format(getString(R.string.video_count), movie.getVote_count()+""));



        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path()).into(backdrop);

        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path()).fit().into(poster);

        overview.setText(movie.getOverview());
        title.setText(movie.getTitle());


        init();
        mPresenter.getSimilarMovies(movie.getId());

    }

    private void init() {

        ApiRest mMovies_api = new ApiRest();
        FakePersistence mMoviePersistence = new FakePersistence();
        MoviesRepository mMoviesRepository = new MoviesRepository(mMoviePersistence, mMovies_api);

        mPresenter = new MovieDetailsPresenter(this, new GetSimilarMovies(mMoviesRepository));
    }

    @Override
    public void showSimilar(List<Movie> movies) {

        LayoutInflater inflater = LayoutInflater.from(this);

        for (final Movie m : movies) {
            Log.d("MOVIEDETAILS", "movie: " + m.getTitle());


            View movieView = inflater.inflate(R.layout.similar_movie, llSimilarMovies, false);
            TextView title = movieView.findViewById(R.id.similar_title);
            ImageView poster = movieView.findViewById(R.id.similar_poster);

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + m.getPoster_path()).fit().into(poster);
            title.setText(m.getTitle());

            movieView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onMovieClicked(m);
                }
            });

            llSimilarMovies.addView(movieView);

        }

    }

    @Override
    public void showProgressBar() {
        llSimilarMovies.setVisibility(View.GONE);
        similarMoviesLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        llSimilarMovies.setVisibility(View.VISIBLE);
        similarMoviesLoading.setVisibility(View.GONE);
    }

    @Override
    public void openMovieDetails(Movie movie) {
        Intent i = new Intent(this, MovieDetails.class);
        i.putExtra("movie", movie);

        startActivity(i);
    }
}
