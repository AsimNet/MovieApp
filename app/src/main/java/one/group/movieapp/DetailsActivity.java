package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieItem>> {

    private static String DETAILS_MOVIE_URL_REQUEST ="http://api.themoviedb.org/3/movie/";

    private static String API_KEY = "?api_key=97b5c78a38a2593d3394c65a9ddb329c";
    private static final int MOVIE_LOADER_ID = 2;

    private LoaderManager LoaderManager;

    private MainListAdapter adapter;

    final String TAG = "DetailActivity";

    @BindView(R.id.list)
    RecyclerView recyclerView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        ButterKnife.bind(this);

        adapter = new MainListAdapter(new ArrayList<MovieItem>(), this);
        recyclerView.setAdapter(adapter);

        String movieId = null;
        if(getIntent().getSerializableExtra("MainActivity.MOVIE_ID_KEY")!= null){
           movieId = (String)getIntent().getSerializableExtra("MainActivity.MOVIE_ID_KEY");
        }

        url = DETAILS_MOVIE_URL_REQUEST + movieId;

        LoaderManager = getLoaderManager();
        LoaderManager.initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<MovieItem>> onCreateLoader(int i, Bundle bundle) {

        url = url + API_KEY;
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

        recyclerView.removeAllViewsInLayout();
        adapter.notifyDataSetChanged();
    }
}
