package myown.themoviesdb.utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import myown.themoviesdb.Constants;
import myown.themoviesdb.activities.filter_activity.FilterActivity;
import myown.themoviesdb.activities.main_activity.MainActivity;
import myown.themoviesdb.activities.movie_details_activity.MovieDetailsActivity;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/5/2017.
 *
 * Navigation Controller controls the navigation of the whole application.
 * In this class we can control the navigation according to our needs and can do the fragments transactions if any.
 */

public class NavigationController {

    /**
     * This method is responsible for starting the Main Activity.
     * @param context is the context of the activity which is starting the Main Activity.
     */
    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * This method is responsible for starting the Movie Detail Activity.
     * @param context is the context of the activity which is starting the Movie Detail Activity.
     * @param movieId is the movie id of the specific movie for which Movie Detail Activity is being started.
     */
    public static void startMovieDetailsActivity(Context context, Integer movieId){
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_ID_EXTRA_KEY, movieId);
        context.startActivity(intent);
    }

    /**
     * This method is responsible fro starting the Movies Filter Activity.
     * @param context is the context of the activity which is starting the Movie Filter Activity. ( Main Activity )
     * @param moviesArray is the unsorted array of movies as got from the API till this time.
     */
    public static void startMoviesFilterActivity(Context context, ArrayList<MoviesResponse.Result> moviesArray){
        Intent intent = new Intent(context, FilterActivity.class);
        intent.putExtra(Constants.UN_FILTERED_MOVIE_ARRAY_KEY, moviesArray);
        context.startActivity(intent);
    }
    
}
