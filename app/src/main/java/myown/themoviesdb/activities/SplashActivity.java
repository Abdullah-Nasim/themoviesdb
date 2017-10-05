package myown.themoviesdb.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import myown.themoviesdb.R;
import myown.themoviesdb.utils.NavigationController;

/**
 * Created by Netaq on 10/5/2017.
 *
 * The SplashActivity is essentially the first activity which is loaded on the application launch.
 * The handler waits for SPLASH_LENGTH amount of time and then calls Navigation Controller to start Main Activity.
 */

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                //Call the NavigationController to start main activity
                NavigationController.startMainActivity(SplashActivity.this);
                finish();

            }
        }, SPLASH_LENGTH);
    }
}
