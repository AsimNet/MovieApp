package one.group.movieapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieItem>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public Loader<List<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<MovieItem>> loader, List<MovieItem> movies) {

    }

    @Override
    public void onLoaderReset(Loader<List<MovieItem>> loader) {

    }
}
