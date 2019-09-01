package com.robertabarrado.movieslist.framework;

import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.robertabarrado.domain.Movie;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "09d3bee2ce75270c7c6e6570ffd20893";

    interface MoviesAPI {


        @GET("movie/{id}")
        Call<Movie> getMovieDetails(@Path("id") int id);

        @GET("movie/popular")
        Call<MoviesPagged> getPopularMovies(@Query("page") int page);


        @GET("movie/{id}/similar")
        Call<MoviesPagged> getSimilarMovies(@Path("id") int id);

    }

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {


                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);


                            Request originalRequest = chain.request();

                            Request.Builder builder = chain.request().newBuilder();

                            HttpUrl url = originalRequest
                                    .url()
                                    .newBuilder()
                                    .addQueryParameter("api_key",API_KEY)
                                    .addQueryParameter("language",Locale.getDefault().getLanguage())
                                    .addQueryParameter("region",Locale.getDefault().getCountry())
                                    .build();
                            Request request = builder.url(url).build();

                            return chain.proceed(request);

                        }
                    }).build())
                    .build();
        }

        return retrofit;
    }


    static MoviesAPI getService() {
        return getRetrofitInstance().create(MoviesAPI.class);
    }

}
