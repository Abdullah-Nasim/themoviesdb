package myown.themoviesdb.network.bals;


import myown.themoviesdb.Constants;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.network.RestClient;
import myown.themoviesdb.network.bals.interfaces.MoviesDetailsFetchListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 10/5/2017.
 */

public class MoviesDetailsBAL {

    public static void getMoviesDetails(String movieId, final MoviesDetailsFetchListener listener){

        Call<MovieDetailsResponse> call = RestClient.getAdapter().getMovieDetails(movieId, Constants.API_KEY, "en-US");

        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {

                //Check if response is successful
                if(response.isSuccessful()) {

                    //Check if response dose not contain empty body
                    if (response.body() != null) {

                        //Movie details successfully fetched
                        listener.onMoviesDetailsFetched(response.body());

                    } else {
                        listener.onMoviesDetailsNotFetched();
                    }

                } else {
                    listener.onMoviesDetailsNotFetched();
                }

            }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {

                //Check the type of Exception
                if(t instanceof IllegalArgumentException){

                    listener.onMoviesDetailsNotFetched();

                } else {
                    listener.onNetworkFailure();
                }

                }
        });
    }
}
