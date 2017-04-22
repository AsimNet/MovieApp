package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieItem>> {

    public final static String MOVIE_ID_KEY = "ID";

    private static final String POPULAR_MOVIE_REQUEST_URL = "http://api.themoviedb.org/3/movie/popular?api_key=f605efa3d15575c1e01480333cb8a356";

    private static final String TOP_RATING_MOVIE_REQUEST_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=f605efa3d15575c1e01480333cb8a356";

    private MainListAdapter adapter;

    private static final int MOVIE_LOADER_ID = 1;

    private LoaderManager LoaderManager;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainListAdapter(new ArrayList<MovieItem>(),this);

        LoaderManager = getLoaderManager();
        LoaderManager.initLoader(MOVIE_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieItem>> loader, List<MovieItem> movies) {

        if (movies != null && !movies.isEmpty()) {
            adapter.reloadList((ArrayList<MovieItem>) movies);
            adapter.notifyItemInserted(movies.size() - 1);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<MovieItem>> loader) {
        

    }
}
