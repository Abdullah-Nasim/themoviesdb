package myown.themoviesdb.activities.movie_details_activity;

import myown.themoviesdb.interfaces.MoviesDetailsFetchListener;
import myown.themoviesdb.interfaces.movie_details_activity.MovieDetailsActivityView;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.network.bals.MoviesDetailsBAL;

/**
 * Created by Netaq on 10/5/2017.
 */

public class MovieDetailsActivityPresenter {

    private MovieDetailsActivityView movieDetailsActivityView;

    public MovieDetailsActivityPresenter(MovieDetailsActivityView movieDetailsActivityView) {
        this.movieDetailsActivityView = movieDetailsActivityView;
    }

    public void performMovieDetailsFetch(Integer movieId){

        MoviesDetailsBAL.getMoviesDetails(movieId, new MoviesDetailsFetchListener() {
            @Override
            public void onMoviesDetailsFetched(MovieDetailsResponse response) {
                movieDetailsActivityView.onMovieDetailsFetched(response);
            }

            @Override
            public void onMoviesDetailsNotFetched() {
                movieDetailsActivityView.onMovieDetailsFetchFailed();
            }

            @Override
            public void onNetworkFailure() {
                movieDetailsActivityView.onNetworkFailure();
            }
        });

    }

}
