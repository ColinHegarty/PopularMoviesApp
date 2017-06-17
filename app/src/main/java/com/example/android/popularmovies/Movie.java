package com.example.android.popularmovies;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Colin on 27/05/2017.
 */

public class Movie {

    private String voteCount;
    private String id;
    private boolean video;
    private String voteAverage;
    private String title;
    private String popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private boolean isAdult;
    private String synopsis;
    private String releaseDate;


    public Movie(JSONObject movie) throws JSONException{
        this.voteCount = movie.getString("vote_count");
        this.id = movie.getString("id");
        this.voteAverage = movie.getString("vote_average");
        this.title = movie.getString("title");
        this.popularity = movie.getString("popularity");
        this.posterPath = movie.getString("poster_path");
        this.originalLanguage = movie.getString("original_language");
        this.originalTitle = movie.getString("original_title");
        this.backdropPath = movie.getString("backdrop_path");

        this.synopsis = movie.getString("overview");
        this.releaseDate = movie.getString("release_date");

        if(movie.getString("adult").equals("false")){
            this.isAdult = false;
        }else{
            this.isAdult = true;
        }
        //TODO SUGGESTION Simplify this if-statement to this.isAdult = movie.getString("adult").equals("false");

        if(movie.getString("video").equals("false")){
            this.video = false;
        }else{
            this.video = true;
        }
        //TODO SUGGESTION Simplify this if-statement as described above.
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
