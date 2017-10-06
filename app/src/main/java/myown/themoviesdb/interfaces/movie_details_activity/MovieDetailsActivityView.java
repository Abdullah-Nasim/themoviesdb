package myown.themoviesdb.interfaces.movie_details_activity;

import myown.themoviesdb.interfaces.GeneralNetworkListener;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This interface is implemented by Movie Details Activity.
 * Movie Details Activity implements the respective abstract method to provide the functionality when these events happens.
 */

public interface MovieDetailsActivityView extends GeneralNetworkListener{

    void onMovieDetailsFetched(MovieDetailsResponse response);

    void onMovieDetailsFetchFailed();

}
