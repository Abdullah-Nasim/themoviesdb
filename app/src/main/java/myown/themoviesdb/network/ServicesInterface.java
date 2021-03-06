package myown.themoviesdb.network;

import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.network.api.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Abdullah on 10/5/2017.
 */


public interface ServicesInterface {

    @GET(Endpoints.POPULAR_MOVIES_ENDPOINT)
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieDetailsResponse> getMovieDetails(@Path("movie_id") Integer movie_id, @Query("api_key") String api_key, @Query("language") String language);

}
