package com.robertabarrado.domain;

import java.io.Serializable;

public class Movie implements Serializable {


    private int id;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;
    private long vote_count;
    private double popularity;
    private String poster_path;
    private String backdrop_path;


    public Movie(int id, String title, double vote_average, String overview, String release_date, long vote_count, long popularity, String poster_path, String backdrop_path) {
        this.id = id;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public long getVote_count() {
        return vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
