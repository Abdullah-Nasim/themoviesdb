package myown.themoviesdb.utils;

import android.content.Context;
import android.content.Intent;

import myown.themoviesdb.activities.MainActivity;

/**
 * Created by Netaq on 10/5/2017.
 */

public class NavigationController {

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
