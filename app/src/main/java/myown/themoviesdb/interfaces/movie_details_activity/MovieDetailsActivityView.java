package myown.themoviesdb.interfaces.movie_details_activity;

import myown.themoviesdb.interfaces.GeneralNetworkListener;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/5/2017.
 */

public interface MovieDetailsActivityView extends GeneralNetworkListener{

    void onMovieDetailsFetched(MovieDetailsResponse response);

    void onMovieDetailsFetchFailed();

}
