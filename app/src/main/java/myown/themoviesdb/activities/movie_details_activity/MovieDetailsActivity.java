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

        movieDetailsActivityPresenter = new MovieDetailsActivityPresenter(this);

        setupActivityTasks();

    }

    private void setupActivityTasks() {

        Integer movieId = getIntent().getIntExtra("Movie_Id", 0);

        Utils.showFullScreenProgress(progressLayout);

        movieDetailsActivityPresenter.performMovieDetailsFetch(movieId);

    }

    @Override
    public void onMovieDetailsFetched(MovieDetailsResponse response) {

        movieTitle.setText(response.getTitle());
        movieTagLine.setText(response.getTagline());
        movieStatus.setText(response.getStatus());
        movieBudget.setText(response.getBudget().toString());
        movieOverview.setText(response.getOverview());
        movieHomePage.setText(response.getHomepage());
        moviePopularity.setText(String.valueOf(response.getPopularity()));
        movieImage.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + response.getPoster_path()));
        movieGenres.setText(Utils.processGenres(response.getGenres()));

        Utils.hideFullScreenProgress(progressLayout);

    }

    @Override
    public void onMovieDetailsFetchFailed() {
        Utils.unableToFetchDataException(MovieDetailsActivity.this);
    }

    @Override
    public void onNetworkFailure() {
        Utils.noInternetException(MovieDetailsActivity.this);
    }

}
