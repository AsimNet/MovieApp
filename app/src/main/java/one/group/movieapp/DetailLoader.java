package one.group.movieapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by MOHANAAD on 4/22/17.
 */

public class DetailLoader extends AsyncTaskLoader<List<MovieItem>> {


private String mUrl;


public DetailLoader(Context context, String url) {
        super(context);
        mUrl = url;
        }

@Override
protected void onStartLoading() {
        forceLoad();
        }


@Override
public List<MovieItem> loadInBackground() {
        if (mUrl == null) {
        return null;
        }

        List<MovieItem> Movies = API.fetchMovieDetails(mUrl);
        return Movies;
        }
        }
