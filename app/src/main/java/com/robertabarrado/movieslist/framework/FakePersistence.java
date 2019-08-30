package com.robertabarrado.movieslist.framework;

import com.robertabarrado.data.MoviePersistence;
import com.robertabarrado.domain.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FakePersistence implements MoviePersistence {


    @Override
    public Movie getMovieDetails(int id) {

        Random r = new Random();

        return new Movie(0, "Test_0", r.nextDouble()%10, "Esta es una historia", new Date(), r.nextLong(), r.nextLong(), "", "");

    }

    @Override
    public List<Movie> getPopularMovies() {
        List<Movie> movieList= new ArrayList<>();
        Random r = new Random();

        for (int i=0; i<20; i++) {
            movieList.add(new Movie(i, "Test_"+i, r.nextDouble()%10, "Esta es una historia", new Date(), r.nextLong(), r.nextLong(), "", ""));
        }
        return movieList;
    }
}
