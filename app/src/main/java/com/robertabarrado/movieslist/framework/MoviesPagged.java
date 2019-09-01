package com.robertabarrado.movieslist.framework;

import com.robertabarrado.domain.Movie;

import java.util.List;

public class MoviesPagged {

    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

    public MoviesPagged(int page, int total_results, int total_pages, List<Movie> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    int getPage() {
        return page;
    }

    int getTotal_results() {
        return total_results;
    }

    int getTotal_pages() {
        return total_pages;
    }

    List<Movie> getResults() {
        return results;
    }
}
