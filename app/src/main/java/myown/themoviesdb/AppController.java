package myown.themoviesdb;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * The AppController is automatically called every time when the application is launched.
 * We will initialize all of the Application level dependencies in onCreate method of this class.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing Fresco
        Fresco.initialize(this);

    }

}
