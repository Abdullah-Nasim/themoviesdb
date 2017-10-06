package myown.themoviesdb.activities.main_activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.themoviesdb.R;
import myown.themoviesdb.adapters.MainRecyclerAdapter;
import myown.themoviesdb.interfaces.main_activity.MainActivityView;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.utils.Utils;

/**
 * Created by Netaq on 10/5/2017.
 *
 * The Main Activity features the list of popular movies from the movie db API.
 * This class contains the implementation of recycler view which follows gird layout structure to display movies.
 * Pagination and Infinite Scroll is also implemented in this class.
 *
 */

public class MainActivity extends AppCompatActivity implements MainActivityView {


    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.main_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private LinearLayoutManager mLayoutManager;

    private MainRecyclerAdapter mAdapter;

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainActivityPresenter = new MainActivityPresenter(this);

        setupActivityTasks();

    }


    /**
     *
     * @param response contains the response from the movie DB API.
     */
    @Override
    public void onMoviesFetched(MoviesResponse response) {

        mAdapter = new MainRecyclerAdapter(response.getResults(), MainActivity.this);

        mainRecycler.setLayoutManager(mLayoutManager);

        mainRecycler.setAdapter(mAdapter);

        swipeRefresh.setRefreshing(false);
    }

    /**
     *
     * @param response contains more results from the API on scroll. (Pagination)
     */
    @Override
    public void onPagination(MoviesResponse response) {

        mAdapter.loadMore(response.getResults());

        mAdapter.notifyDataSetChanged();

    }

    /**
     * In case - unable to fetch movies list from the movie DB API.
     */
    @Override
    public void onMoviesFetchedFailed() {

        Utils.unableToFetchDataException(MainActivity.this);

    }

    /**
     * In case - no internet connection available.
     */
    @Override
    public void onNetworkFailure() {

        Utils.noInternetException(MainActivity.this);

    }

    /**
     * This method is responsible for setting up the components of the AMin Activity.
     * This method also calls the presenter to perform specific tasks.
     */
    private void setupActivityTasks() {

        swipeRefresh.setEnabled(false);

        swipeRefresh.setRefreshing(true);

        mLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);

        mainActivityPresenter.performMoviesFetch();

        mainActivityPresenter.loadMoreMovieItems(mainRecycler,swipeRefresh,mLayoutManager);

    }
}
