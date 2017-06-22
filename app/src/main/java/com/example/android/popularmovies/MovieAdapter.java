package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Colin on 01/06/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private int mNumberItems;
    private ArrayList<Movie> listOfMovies;
    private Context context;

    final private ListItemClickListener mOnClickListener;


    public MovieAdapter(ArrayList<Movie> movieList, int size, ListItemClickListener listItemClickListener){
        Log.e("MovieAdapter:", "Setting up the adapter");
        this.mNumberItems = size;
        this.listOfMovies = movieList;
        this.mOnClickListener = listItemClickListener;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Log.e("OnCreateViewHolder:", "CreatingViewHolder");
        int layoutIdForListItem = R.layout.movie_item;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
        Log.e("OnBindViewHolder:", "Binding The View");
        int width = context.getResources().getDisplayMetrics().widthPixels;
        Picasso.with(context).load(context.getResources().getString(R.string.poster_prefix)+ listOfMovies.get(position).getPosterPath()).resize(width/2, 0).into(holder.listItemMovieView);
    }

    @Override
    public int getItemCount() {
        Log.e("getItemCount:", "getting the old Item Count" + listOfMovies.size());
        return listOfMovies.size();
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView listItemMovieView;

        public MovieViewHolder(View itemView){
            super(itemView);
            Log.e("MovieViewHolder:", "Creating Inner Class Movie View Holder");

            listItemMovieView = (ImageView) itemView.findViewById(R.id.movie_item_number);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.e("OnClick:", "MovieViewHolder has been clicked");
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClicked(clickedPosition);
        }
    }

    public interface ListItemClickListener{
        void onListItemClicked(int clickedItemIndex);
    }

}
