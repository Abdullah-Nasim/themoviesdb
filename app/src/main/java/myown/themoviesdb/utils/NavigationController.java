package myown.themoviesdb.utils;

import android.content.Context;
import android.content.Intent;

import myown.themoviesdb.activities.main_activity.MainActivity;
import myown.themoviesdb.activities.movie_details_activity.MovieDetailsActivity;

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
        intent.putExtra("Movie_Id", movieId);
        context.startActivity(intent);
    }
    
}
