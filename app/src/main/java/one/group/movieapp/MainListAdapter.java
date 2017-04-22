package one.group.movieapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asimaltwijry on 4/22/17.
 */

class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MovieViewHolder> {

    private List<MovieItem> movies;
    private Context context;
    static final String THUMBNAIL_URL = "http://image.tmdb.org/t/p/w185";
    final String TAG = "MainListAdapter";

    public MainListAdapter(ArrayList<MovieItem> movies, Context context) {
        this.movies = movies;
        this.context = context;
        Log.i(TAG, "MainListAdapter: ");
    }

    public void reloadList(ArrayList<MovieItem> mMovies) {
        this.movies.addAll(mMovies);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        MovieItem currentMovie = this.movies.get(position);
        holder.currentMovie = currentMovie;
        holder.context = context;
        holder.movieTitle.setText(currentMovie.getTitle());
        Picasso.with(context).load(THUMBNAIL_URL + currentMovie.getImage()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Log.i(TAG, "onBitmapLoaded: w: "+width +", h:"+height);
                holder.moviePic.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

                Log.i(TAG, "onBitmapFailed: "+errorDrawable.toString());
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_pic)
        ImageView moviePic;

        @BindView(R.id.movie_title)
        TextView movieTitle;

        @BindView(R.id.card_view)
        CardView cv;


        MovieItem currentMovie;

        Context context;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    // Create a new intent to view the earthquake URI
                    Intent DetailsIntent = new Intent(context, DetailsActivity.class);
                    Bundle extras = new Bundle();

                    extras.putString(MainActivity.MOVIE_ID_KEY, String.valueOf(currentMovie.getMovieId()));
                    DetailsIntent.putExtras(extras);

                    // Send the intent to launch a new activity
                    context.startActivity(DetailsIntent);
                }
            });
        }
    }
}
