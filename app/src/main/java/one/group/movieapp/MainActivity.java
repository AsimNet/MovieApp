package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        connectToAPI();


    }


    @Override
    public Loader<List<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        Log.i(TAG, "onCreateLoader: "+ url);
        return new MovieLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieItem>> loader, List<MovieItem> movies) {

        Log.i(TAG, "onLoadFinished: ");
        // Hide loading indicator because the data has been loaded
        loadingIndicator.setVisibility(View.GONE);

        if (movies != null && !movies.isEmpty()) {
            adapter.reloadList((ArrayList<MovieItem>) movies);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<MovieItem>> loader) {

// Loader reset, so we can clear out our existing data.
        recyclerView.removeAllViewsInLayout();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void connectToAPI() {
        noMovieFoundFrame.setVisibility(View.GONE);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.


            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            //  loaderManager.initLoader(1, null, this);

            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);


        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            loadingIndicator.setVisibility(View.GONE);

            noMovieFoundFrame.setVisibility(View.VISIBLE);
            // Update empty state with no connection error message
            noMovieFoundView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle(getResources().getString(R.string.sort_by));
            String[] types = {getResources().getString(R.string.popular), getResources().getString(R.string.top_rated)};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    switch (which) {
                        case 0:
                            url = POPULAR_MOVIE_REQUEST_URL;
                            connectToAPI();
                            break;
                        case 1:
                            url = TOP_RATING_MOVIE_REQUEST_URL;
                            connectToAPI();
                            break;
                    }
                }

            });

            b.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
