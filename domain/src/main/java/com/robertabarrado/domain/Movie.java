package com.robertabarrado.domain;

import java.util.Date;

public class Movie {


    private int id;
    private String title;
    private double vote_average;
    private String overview;
    private Date release_data;
    private long video_count;
    private long popularity;
    private String poster_path;
    private String backdrop_path;


    public Movie(int id, String title, double vote_average, String overview, Date release_data, long video_count, long popularity, String poster_path, String backdrop_path) {
        this.id = id;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_data = release_data;
        this.video_count = video_count;
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

    public Date getRelease_data() {
        return release_data;
    }

    public long getVideo_count() {
        return video_count;
    }

    public long getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
