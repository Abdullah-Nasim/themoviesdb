package myown.themoviesdb;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Netaq on 10/5/2017.
 */

public class AppController extends Application {

    /**
     * The AppController is automatically called every time when the application is loaded.
     * We will initialize all of the Application level dependencies in onCreate method of this class.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing Fresco
        Fresco.initialize(this);

    }

}
