package myown.themoviesdb.network.bals.interfaces;


import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/5/2017.
 */

public interface MoviesFetchListener extends GeneralNetworkListener{

    void onMoviesFetched(MoviesResponse response);
    void onMoviesNotFetched();
}
