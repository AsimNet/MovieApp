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


    public MovieItem(int movieId, String title, String image) {
        mMovieId = movieId;
        mTitle = title;
        mImage = image;
    }

    public MovieItem(int movieId, String title, String image, String releaseDate, String poster, String vote) {
        mMovieId = movieId;
        mTitle = title;
        mImage = image;
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
