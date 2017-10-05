package myown.themoviesdb.network.bals.interfaces;


import myown.themoviesdb.models.MovieDetailsResponse;

/**
 * Created by Netaq on 10/5/2017.
 */

public interface MoviesDetailsFetchListener extends GeneralNetworkListener{

    void onMoviesDetailsFetched(MovieDetailsResponse response);
    void onMoviesDetailsNotFetched();
}
