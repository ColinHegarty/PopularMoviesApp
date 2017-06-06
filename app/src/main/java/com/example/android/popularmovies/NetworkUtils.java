package com.example.android.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by User on 23-May-17.
 */

public class NetworkUtils {

    public static URL buildURL(String TMDB_BASE_URL){
        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon().build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();

            Scanner sc = new Scanner(in);
            sc.useDelimiter("\\A");

            boolean hasInput = sc.hasNext();
            if(hasInput){
                return sc.next();
            }else{
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
