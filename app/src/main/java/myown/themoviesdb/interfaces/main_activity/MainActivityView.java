package myown.themoviesdb.interfaces.main_activity;

import myown.themoviesdb.interfaces.GeneralNetworkListener;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This interface is implemented by Main Activity.
 * Main Activity implements the respective abstract method to provide the functionality when these events happens.
 */

public interface MainActivityView extends GeneralNetworkListener{

    void onMoviesFetched(MoviesResponse response);

    void onPagination(MoviesResponse response);

    void onMoviesFetchedFailed();

}
