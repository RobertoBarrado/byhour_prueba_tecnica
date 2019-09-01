package com.robertabarrado.movieslist.framework;

import android.util.Log;

import com.robertabarrado.data.Movies_API;
import com.robertabarrado.domain.Movie;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class ApiRest implements Movies_API {


    @Override
    public Movie getMovieDetails(int id) {

        RetrofitInstance.MoviesAPI api = RetrofitInstance.getService();
        Call<Movie> call = api.getMovieDetails(id);
        Movie movie = null;
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                movie = (Movie) response.body();
            }
            else Log.e("ApiRest", "FAIL: " + response.code());
        } catch (IOException e) {
            Log.e("ApiRest", e.getLocalizedMessage());
        }

        return movie;
    }

    @Override
    public List<Movie> getPopularMovies(int page) {
        RetrofitInstance.MoviesAPI api = RetrofitInstance.getService();
        Call<MoviesPagged> call = api.getPopularMovies(page);
        List<Movie> movies = null;
        
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                MoviesPagged pag = (MoviesPagged) response.body();
                movies = pag != null ? pag.getResults() : null;
            }
            else Log.e("ApiRest", "FAIL: " + response.code());
        } catch (IOException e) {
            Log.e("ApiRest", e.getLocalizedMessage());
        }

        return movies;
    }

    @Override
    public List<Movie> getSimilarMovies(int id) {
        RetrofitInstance.MoviesAPI api = RetrofitInstance.getService();
        Call<MoviesPagged> call = api.getSimilarMovies(id);
        List<Movie> movies = null;

        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                MoviesPagged pag = (MoviesPagged) response.body();
                movies = pag != null ? pag.getResults() : null;
            }
            else Log.e("ApiRest", "FAIL: " + response.code());
        } catch (IOException e) {
            Log.e("ApiRest", e.getLocalizedMessage());
        }

        return movies;
    }
}
