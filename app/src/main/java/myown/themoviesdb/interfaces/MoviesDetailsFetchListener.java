package myown.themoviesdb.interfaces;


import myown.themoviesdb.models.MovieDetailsResponse;

/**
 * Created by Abdullah on 10/5/2017.
 */

public interface MoviesDetailsFetchListener extends GeneralNetworkListener{

    void onMoviesDetailsFetched(MovieDetailsResponse response);
    void onMoviesDetailsNotFetched();
}
