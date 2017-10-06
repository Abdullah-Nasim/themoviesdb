package myown.themoviesdb.interfaces;


import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Abdullah on 10/5/2017.
 */

public interface MoviesFetchListener extends GeneralNetworkListener{

    void onMoviesFetched(MoviesResponse response);
    void onMoviesNotFetched();
}
