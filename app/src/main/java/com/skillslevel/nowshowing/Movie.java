package com.skillslevel.nowshowing;

public class Movie {

    private String title;
    private Double vote_average;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private String release_date;
    private String adult;

    public Movie(String title, Double vote_average, String poster_path, String backdrop_path,
                 String overview, String release_date, String adult) {
        this.title = title;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.release_date = release_date;
        this.adult = adult;
    }

    public String getTitle() {
        return title;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getAdult() {
        return adult;
    }
}
