package one.group.movieapp;

/**
 * Created by MOHANAAD on 4/22/17.
 */

public class MovieItem {

    private int mMovieId;

    private String mTitle;

    private String mImage;

    private String mReleaseDate;

    private String mPoster;

    private String mVote;

    private String mOverView;

    public MovieItem(int movieId, String title, String image) {
        mMovieId = movieId;
        mTitle = title;
        mImage = image;
    }

    public MovieItem( String title, String overView, String releaseDate, String poster, String vote) {

        mTitle = title;
        mOverView = overView;
        mReleaseDate = releaseDate;
        mPoster = poster;
        mVote = vote;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getVote() {
        return mVote;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public String getOverView(){return mOverView;}

    @Override
    public String toString() {
        return "MovieItem{" +
                "mMovieId=" + mMovieId +
                ", mTitle='" + mTitle + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPoster='" + mPoster + '\'' +
                ", mVote='" + mVote + '\'' +
                '}';
    }
}
