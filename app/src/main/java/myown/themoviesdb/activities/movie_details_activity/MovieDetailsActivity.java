package myown.themoviesdb.activities.movie_details_activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.themoviesdb.Constants;
import myown.themoviesdb.R;
import myown.themoviesdb.interfaces.movie_details_activity.MovieDetailsActivityView;
import myown.themoviesdb.models.MovieDetailsResponse;
import myown.themoviesdb.utils.Utils;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This class features the details of selected movie from the Main Recycler - Main Activity.
 * In this class all of the view components are assign their respective values.
 */

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsActivityView{

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

    private MovieDetailsActivityPresenter movieDetailsActivityPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        // Initializing the respective presenter. All of the functionality for this Activity can be found in this presenter class.
        movieDetailsActivityPresenter = new MovieDetailsActivityPresenter(this);

        setupActivityTasks();

    }

    private void setupActivityTasks() {

        //Getting the movie ID from the intent added by MainRecyclerAdapter.
        Integer movieId = getIntent().getIntExtra(Constants.MOVIE_ID_EXTRA_KEY, 0);

        // Show full screen progress layout till the movie details are loaded.
        Utils.showFullScreenProgress(progressLayout);

        // Calling the Movie Details Presenter to start the task of fetching the movie details.
        movieDetailsActivityPresenter.performMovieDetailsFetch(movieId);

    }

    /**
     * This method is called when the presenter finishes the task of fetching the movie details.
     * @param response contains the response of movie details API.
     */
    @Override
    public void onMovieDetailsFetched(MovieDetailsResponse response) {

        // Assigning the view components their respective values.
        movieTitle.setText(response.getTitle());
        movieTagLine.setText(response.getTagline());
        movieStatus.setText(response.getStatus());
        movieBudget.setText(response.getBudget().toString());
        movieOverview.setText(response.getOverview());
        movieHomePage.setText(response.getHomepage());
        moviePopularity.setText(String.valueOf(response.getPopularity()));
        movieImage.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + response.getPoster_path()));
        movieGenres.setText(Utils.processGenres(response.getGenres()));

        // The movie details are successfully loaded and assigned. So, we can hide the full screen progress layout.
        Utils.hideFullScreenProgress(progressLayout);

    }

    /**
     * This method is called when the presenter is unable to fetch the movie details.
     */
    @Override
    public void onMovieDetailsFetchFailed() {
        Utils.unableToFetchDataException(MovieDetailsActivity.this);
    }

    /**
     * This method is called when the presenter finds out that there is no internet connection.
     */
    @Override
    public void onNetworkFailure() {
        Utils.noInternetException(MovieDetailsActivity.this);
    }

}
