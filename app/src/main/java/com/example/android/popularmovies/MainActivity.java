package com.example.android.popularmovies;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{

    private TextView mTextView;

    private MovieAdapter mAdapter;
    private RecyclerView mMovieList;
    private MainActivity mainActivity = this;
    private ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextView = (TextView) findViewById(R.id.textView_default);
        //mTextView.setText(getResources().getString(R.string.welcome));
        listOfMovies.clear();
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);

        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);
        mAdapter = new MovieAdapter(getApplicationContext(),listOfMovies, this);

        connectToTMDB("popular");



        //TODO REQUIREMENT Movies should be displayed in the main layout once the app starts, device orientation changes etc.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.popular:
                                listOfMovies.clear();
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_popular) , Toast.LENGTH_LONG).show();
                                connectToTMDB("popular");
                                break;
            //TODO REQUIREMENT Move string literals to strings.xml or use constants as appropriate DONE
            case R.id.top_rated:
                                listOfMovies.clear();
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_top) , Toast.LENGTH_LONG).show();
                                connectToTMDB("top_rated");
                                break;
        }
        //TODO REQUIREMENT Follow the Java Coding & Styling guidelines. DONE

        return super.onOptionsItemSelected(item);
    }

    public void connectToTMDB(String query){
        String connectionString = "https://api.themoviedb.org/3/movie/"+ query + "?api_key=" + getResources().getString(R.string.api_key);
        URL movieSearchUrl =  NetworkUtils.buildURL(connectionString);
        new GetTMDBResults().execute(movieSearchUrl);
    }

    private void setUpRecyclerView(){
        Log.e("setUpRecyclerView:", " entering method");




        mMovieList.setAdapter(mAdapter);




//        mTextView.setVisibility(View.INVISIBLE);

//        mMovieList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        if(mToast != null){
            mToast.cancel();
        }
        Movie movie = listOfMovies.get(clickedItemIndex);
        String toastMessage = movie.getTitle();
        mToast = Toast.makeText(mainActivity, toastMessage, Toast.LENGTH_LONG);
        mToast.show();


        Class destination = MovieDetails.class;
        Intent intentToStartAct = new Intent(this, destination);
        Bundle extras = new Bundle();
        extras.putString("BACKDROP_URL", movie.getBackdropPath());
        extras.putString("SYNOPSIS",movie.getSynopsis());
        extras.putString("TITLE", movie.getTitle());
        extras.putString("RELEASE_DATE", movie.getReleaseDate());
        extras.putString("VOTE_AVERAGE", movie.getVoteAverage());

        intentToStartAct.putExtras(extras);
        startActivity(intentToStartAct);
    }

    public class GetTMDBResults extends AsyncTask<URL, Void, String>{
        //TODO SUGGESTION Consider using AsyncTaskLoader

        @Override
        protected String doInBackground(URL... params) {
            URL searchURL = params[0];
            String TMDBResults = null;
            try{
                TMDBResults = NetworkUtils.getResponseFromHttpUrl(searchURL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(TMDBResults != null && !TMDBResults.equals("")) {
                jsonParser(TMDBResults);
            }

            return TMDBResults;
            //TODO AWESOME You're doing network operations on a background thread.
        }

        @Override
        protected void onPostExecute(String TMDBResults){
            Log.e("On Post:", "Results back: "+TMDBResults);
                setUpRecyclerView();

//            setUpRecyclerView();
//            if(TMDBResults != null && !TMDBResults.equals("")){
//                jsonParser(TMDBResults);
                //TODO SUGGESTION Consider moving this into doInBackground rather than doing it on your UI thread
//            }
        }

        private void jsonParser(String TMDBResults){
            JSONObject results = null;
            JSONArray movie = null;
            try{
                results = new JSONObject(TMDBResults);
                movie = results.getJSONArray("results");

                for(int i=0; i<movie.length(); i++){
                    Movie mov = new Movie(movie.getJSONObject(i));
                    listOfMovies.add(mov);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }
        }



    }


}
