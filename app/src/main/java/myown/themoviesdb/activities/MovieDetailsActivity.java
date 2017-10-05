package myown.themoviesdb.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.themoviesdb.R;
import myown.themoviesdb.utils.Utils;

/**
 * Created by Netaq on 10/5/2017.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_tag_line)
    TextView movieTagLine;

    @BindView(R.id.movie_image)
    SimpleDraweeView movieImage;

    @BindView(R.id.movie_genres)
    TextView movieGenres;

    @BindView(R.id.movie_home_page)
    TextView movieHomePage;

    @BindView(R.id.movie_status)
    TextView movieStatus;

    @BindView(R.id.movie_budget)
    TextView movieBudget;

    @BindView(R.id.movie_popularity)
    TextView moviePopularity;

    @BindView(R.id.movie_overview)
    TextView movieOverview;

    @BindView(R.id.progress_layout)
    FrameLayout progressLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        //Check the movie id in intent extras.
        Integer movieId = getIntent().getIntExtra("Movie_Id", 0);


        //The following method call is responsible for calling the movie details api and setting up the views
        getMovieDetails(movieId);

    }

    /**
     *
     * @param movieId is the Id of the movie selected from the main recycler view.
     */
    private void getMovieDetails(Integer movieId) {

        Utils.showFullScreenProgress(progressLayout);

    }
}
