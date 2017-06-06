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

    public MovieAdapter(Context context, ArrayList<Movie> movieList, ListItemClickListener listItemClickListener){

        this.context = context;
        this.mNumberItems = movieList.size();
        this.listOfMovies = movieList;
        this.mOnClickListener = listItemClickListener;

    }

    public interface ListItemClickListener{
        void onListItemClicked(int clickedItemIndex);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView listItemMovieView;

        public MovieViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            listItemMovieView = (ImageView) itemView.findViewById(R.id.movie_item_number);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClicked(clickedPosition);
        }
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        int layoutIdForListItem = R.layout.movie_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        Picasso.with(context).load(context.getResources().getString(R.string.poster_prefix)+ listOfMovies.get(position).getPosterPath()).resize(width/2, 0).into(holder.listItemMovieView);
    }


    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }



}
