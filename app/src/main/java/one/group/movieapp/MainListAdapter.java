package one.group.movieapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asimaltwijry on 4/22/17.
 */

class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Context context;
    private static final String THUMBNAIL_URL = "http://image.tmdb.org/t/p/w185";


    public MainListAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public void reloadList(ArrayList<Movie> mMovies) {
        this.movies.addAll(mMovies);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currentMovie = this.movies.get(position);
        holder.currentMovie = currentMovie;
        holder.context = context;
        holder.movieTitle.setText(currentMovie.getTitle);
        Picasso.with(context).load(THUMBNAIL_URL + currentMovie.getImage()).into(holder.moviePic);


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


        Movie currentMovie;

        Context context;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    // Create a new intent to view the earthquake URI
                    Intent DetailsIntent = new Intent(context, DetailsActivity.class);
                    DetailsIntent.putExtra("ID", currentMovie.getIid);

                    // Send the intent to launch a new activity
                    context.startActivity(DetailsIntent);
                }
            });
        }
    }
}
