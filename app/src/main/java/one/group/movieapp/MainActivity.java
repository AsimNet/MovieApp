package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieItem>> {

    public final static String MOVIE_ID_KEY = "ID";

    private static final String POPULAR_MOVIE_REQUEST_URL = "http://api.themoviedb.org/3/movie/popular?api_key=97b5c78a38a2593d3394c65a9ddb329c";

    private static final String TOP_RATING_MOVIE_REQUEST_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=97b5c78a38a2593d3394c65a9ddb329c";

    private MainListAdapter adapter;

    private static final int MOVIE_LOADER_ID = 1;

    private LoaderManager LoaderManager;

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    final String TAG = "MainActivity";
    String url;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.loading_indicator)
    View loadingIndicator;

    @BindView(R.id.noInternetConnection)
    TextView noMovieFoundView;

    @BindView(R.id.noInternetConnectionFrame)
    FrameLayout noMovieFoundFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        adapter = new MainListAdapter(new ArrayList<MovieItem>(), this);
        recyclerView.setAdapter(adapter);
        url = POPULAR_MOVIE_REQUEST_URL;
        LoaderManager = getLoaderManager();
        LoaderManager.initLoader(MOVIE_LOADER_ID, null, this);


    }

    @Override
    public Loader<List<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieItem>> loader, List<MovieItem> movies) {

        Log.i(TAG, "onLoadFinished: ");

        for (int i = 0 ; i< movies.size() ; i++){
            Log.i(TAG, "onLoadFinished: "+movies.get(i).toString());
        }
        if (movies != null && !movies.isEmpty()) {
            adapter.reloadList((ArrayList<MovieItem>) movies);
            adapter.notifyItemInserted(movies.size() - 1);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<MovieItem>> loader) {

// Loader reset, so we can clear out our existing data.
        recyclerView.removeAllViewsInLayout();
        adapter.notifyDataSetChanged();
    }
}
