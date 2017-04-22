package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<MovieItem> {

    private static String DETAILS_MOVIE_URL_REQUEST ="http://api.themoviedb.org/3/movie/";

    private static String API_KEY = "?api_key=97b5c78a38a2593d3394c65a9ddb329c";
    private static final int MOVIE_LOADER_ID = 2;

    private LoaderManager LoaderManager;

    final String TAG = "DetailActivity";

    String url;

    @BindView(R.id.perceived_magnitude)
    TextView vote;

    @BindView(R.id.title1)
    TextView title;

    @BindView(R.id.release)
    TextView releaseDate;

    @BindView(R.id.overview)
    TextView overView;

    @BindView(R.id.poster)
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        String movieId = null;
        Bundle data = getIntent().getExtras();

        if(data.getString(MainActivity.MOVIE_ID_KEY) != null){
           movieId = data.getString(MainActivity.MOVIE_ID_KEY);
        }

        url = DETAILS_MOVIE_URL_REQUEST + movieId;

        LoaderManager = getLoaderManager();
        LoaderManager.initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public Loader<MovieItem> onCreateLoader(int i, Bundle bundle) {

        url = url + API_KEY;
        Log.i(TAG, "onCreateLoader: "+url );
        return new DetailLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<MovieItem> loader, MovieItem movie) {

        Log.i(TAG, "onLoadFinished: ");

        if (movie != null ) {

            vote.setText(movie.getVote());
            title.setText(movie.getTitle());
            releaseDate.setText(movie.getReleaseDate());
            overView.setText(movie.getOverView());
            Log.i(TAG, "onLoadFinished: "+MainListAdapter.THUMBNAIL_URL+movie.getPoster() + movie.getImage());
            Picasso.with(getApplicationContext()).load(MainListAdapter.THUMBNAIL_URL+movie.getPoster() + movie.getImage()).into(poster);
        }

    }

    @Override
    public void onLoaderReset(Loader<MovieItem> loader) {
        loader.cancelLoad();
    }
}
