package myown.themoviesdb.utils;

import android.content.Context;
import android.content.Intent;

import myown.themoviesdb.activities.MainActivity;
import myown.themoviesdb.activities.MovieDetailsActivity;

/**
 * Created by Netaq on 10/5/2017.
 */

public class NavigationController {

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startMovieDetailsActivity(Context context, Integer movieId){
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("Movie_Id", movieId);
        context.startActivity(intent);
    }
}
