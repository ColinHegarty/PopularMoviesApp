package com.example.android.popularmovies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewSynopsis;
    private TextView textViewRating;
    private TextView textViewTitle;
    private TextView textViewReleaseDate;
    private static final String TAG = MovieDetails.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageView = (ImageView) findViewById(R.id.imageView);
        textViewSynopsis= (TextView) findViewById(R.id.synopsis);
        textViewRating= (TextView) findViewById(R.id.average_rating);
        textViewTitle= (TextView) findViewById(R.id.title);
        textViewReleaseDate= (TextView) findViewById(R.id.release_date);

        Intent intentThatStarted = getIntent();

        if (intentThatStarted != null) {
            Bundle movie = intentThatStarted.getExtras();
            textViewTitle.setText("Title: " + movie.getString("TITLE"));
            textViewTitle.setTypeface(null, Typeface.BOLD_ITALIC);
            textViewSynopsis.setText("Synopsis:\n"+ movie.getString("SYNOPSIS"));
            textViewRating.setText("Rating: " + movie.getString("VOTE_AVERAGE"));
            textViewReleaseDate.setText("Release Date: "+movie.getString("RELEASE_DATE"));


            Picasso.with(this).load(getResources().getString(R.string.poster_prefix)+ movie.getString("BACKDROP_URL")).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }

    }
}
