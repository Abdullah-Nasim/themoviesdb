package myown.themoviesdb.interfaces.main_activity;

import myown.themoviesdb.interfaces.GeneralNetworkListener;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/5/2017.
 */

public interface MainActivityView extends GeneralNetworkListener{

    void onMoviesFetched(MoviesResponse response);

    void onPagination(MoviesResponse response);

    void onMoviesFetchedFailed();

}
