package myown.themoviesdb.activities.main_activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.themoviesdb.Constants;
import myown.themoviesdb.R;
import myown.themoviesdb.adapters.MainRecyclerAdapter;
import myown.themoviesdb.interfaces.main_activity.MainActivityView;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.utils.NavigationController;
import myown.themoviesdb.utils.Utils;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * The Main Activity features the list of popular movies from the movie db API.
 * This class contains the implementation of recycler view which follows gird layout structure to display movies.
 * Pagination and Infinite Scroll is also implemented in this class.
 * This Activity is started in the "Single Task" launch mode.
 */

public class MainActivity extends AppCompatActivity implements MainActivityView {


    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.main_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private LinearLayoutManager mLayoutManager;

    private MainRecyclerAdapter mAdapter;

    private MainActivityPresenter mainActivityPresenter;

    private ArrayList<MoviesResponse.Result> moviesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Initializing the respective presenter. All of the functionality for this Activity can be found in this presenter class.
        mainActivityPresenter = new MainActivityPresenter(this);

        setupActivityTasks();

    }

    /**
     * This method is called when the filter applied button is clicked and new intent of Main Activity is started.
     * Please note that the Main Activity is running in "Single Task" launch mode.
     * Any new intent of Main activity created and started by any other activity will result in callback to this method.
     * @param intent holds the new intent created by Filter Activity.
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Setting the filter applied to tell the presenter that the filter is applied. Now block the pagination.
        mainActivityPresenter.setFilterApplied(true);

        ArrayList<MoviesResponse.Result> filteredMoviesArray = (ArrayList<MoviesResponse.Result>) intent.getSerializableExtra(Constants.FILTERED_MOVIE_ARRAY_KEY);

        mAdapter.setDataSet(filteredMoviesArray);

        mAdapter.notifyDataSetChanged();

    }

    /**
     * This method will be called when the movies list is fetched successfully by the presenter.
     * This method is responsible of setting the mainRecycler adapter and its layout manager.
     * @param response contains the response from the movie DB API.
     */
    @Override
    public void onMoviesFetched(MoviesResponse response) {

        // In case of refresh after filtered applied. Re - Initializing the empty array to free the previously used memory.
        moviesArray = new ArrayList<>();

        moviesArray = response.getResults();

        mAdapter = new MainRecyclerAdapter(response.getResults(), MainActivity.this);

        mainRecycler.setLayoutManager(mLayoutManager);

        mainRecycler.setAdapter(mAdapter);

    }

    /**
     * This method is called when the user reaches the end of scroll and the following page of movies list is loaded by the presenter.
     * This method is also responsible for add the fetched movies in the previous data set of the adapter.
     * This method also notify the adapter about some change in data set.
     * this method will be called infinitely when the user keeps on scrolling. (Infinite Scroll)
     * @param response contains more results from the API on scroll. (Pagination)
     */
    @Override
    public void onPagination(MoviesResponse response) {

        moviesArray.addAll(response.getResults());

        mAdapter.loadMore(response.getResults());

        mAdapter.notifyDataSetChanged();

    }

    /**
     * This method is called when the BAL is unable to fetch the data from the API.
     * In case - unable to fetch movies list from the movie DB API.
     */
    @Override
    public void onMoviesFetchedFailed() {

        Utils.unableToFetchDataException(MainActivity.this);
    }

    /**
     * This method is called when the BAL founds that there is no internet connection.
     * In case - no internet connection available.
     */
    @Override
    public void onNetworkFailure() {

        Utils.noInternetException(MainActivity.this);
    }

    // For creating a filter menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_filter){

            //Check if the movies are still loading
            if(moviesArray.size()>0) {
                NavigationController.startMoviesFilterActivity(MainActivity.this, moviesArray);
            }else{
                Toast.makeText(this, R.string.movies_still_loading_error, Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is responsible for setting up the components of the Main Activity.
     * This method also calls the presenter to perform specific tasks.
     */
    private void setupActivityTasks() {

        // Setting the filter applied to tell the presenter that filter is not applied yet.
        mainActivityPresenter.setFilterApplied(false);

        // Setting up the layout manager for thr main recycler
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);

        // Initializing moviesArray. This array will hold the complete list of movies loaded till this activity is running.
        moviesArray = new ArrayList<>();

        // Calling the presenter to perform movies fetch
        mainActivityPresenter.performMoviesFetch(swipeRefresh);

        // Calling the presenter to setup on scroll change listener
        mainActivityPresenter.loadMoreMovieItems(mainRecycler,swipeRefresh,mLayoutManager);

        // Calling the presenter to setup on swipe refresh listener
        mainActivityPresenter.onSwipeRefresh(swipeRefresh);


    }
}
