package myown.themoviesdb.network.bals;

import myown.themoviesdb.Constants;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.network.RestClient;
import myown.themoviesdb.interfaces.MoviesFetchListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This business access layer is responsible to call the movie db API for getting popular movies list.
 * In case of success or failure this BAL will notify the listener of respective interface about what ever event happens.
 */

public class PopularMoviesBAL {

    public static void getPopularMovies(int page, final MoviesFetchListener listener){

        Call<MoviesResponse> call = RestClient.getAdapter().getPopularMovies(Constants.API_KEY, "en-US", page);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                //Check if response is successful
                if(response.isSuccessful()) {

                    //Check if response dose not contain empty array
                    if (response.body().getResults().size() != 0) {

                        //Popular Movies successfully fetched
                        listener.onMoviesFetched(response.body());

                    } else {
                        //Popular Movies not fetched.
                        listener.onMoviesNotFetched();
                    }

                } else {
                    //Popular movies not fetched.
                    listener.onMoviesNotFetched();
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                //Check the type of Exception
                if(t instanceof IllegalArgumentException){
                    //Some other exception.
                    listener.onMoviesNotFetched();

                } else {
                    //No internet exception.
                    listener.onNetworkFailure();
                }

                }
        });
    }
}
