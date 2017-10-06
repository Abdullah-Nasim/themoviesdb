package myown.themoviesdb.activities.movie_details_activity;

import myown.themoviesdb.interfaces.MoviesDetailsFetchListener;
import myown.themoviesdb.interfaces.movie_details_activity.MovieDetailsActivityView;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.network.bals.MoviesDetailsBAL;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This is the presenter class for the Movie Details Activity.
 * All of the functionality and app logic about the movie Details Activity is implemented in this class.
 * This class notifies the listeners after some event is completed.
 */

public class MovieDetailsActivityPresenter {

    private MovieDetailsActivityView movieDetailsActivityView;

    public MovieDetailsActivityPresenter(MovieDetailsActivityView movieDetailsActivityView) {
        this.movieDetailsActivityView = movieDetailsActivityView;
    }

    /**
     * This method is responsible for calling the Movies Details BAL to fetch movie details from the movie details API.
     * The method handles the success and the failure cases specific to the respective network call.
     * @param movieId contains the movie Id which is required by the movie details API to fetch the details about the specific movie.
     */
    public void performMovieDetailsFetch(Integer movieId){

        MoviesDetailsBAL.getMoviesDetails(movieId, new MoviesDetailsFetchListener() {

            /**
             * This method is called when the BAL successfully fetches the movies details.
             * @param response contains the response from the Movies Details API.
             */
            @Override
            public void onMoviesDetailsFetched(MovieDetailsResponse response) {
                movieDetailsActivityView.onMovieDetailsFetched(response);
            }

            /**
             * This method is called when the BAL is unable to call get the response from the server. Or there is some other exception or error
             */
            @Override
            public void onMoviesDetailsNotFetched() {
                movieDetailsActivityView.onMovieDetailsFetchFailed();
            }

            /**
             * This method is called when the BAL finds out that there is no internet connection.
             */
            @Override
            public void onNetworkFailure() {
                movieDetailsActivityView.onNetworkFailure();
            }
        });

    }

}
