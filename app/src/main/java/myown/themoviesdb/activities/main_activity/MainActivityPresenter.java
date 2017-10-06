package myown.themoviesdb.activities.main_activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import myown.themoviesdb.interfaces.main_activity.MainActivityView;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.network.bals.PopularMoviesBAL;
import myown.themoviesdb.interfaces.MoviesFetchListener;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This is the presenter class for the Main Activity.
 * All of the functionality and app logic about the Main Activity is implemented in this class.
 * This class notifies the listeners after some event is completed.
 */

public class MainActivityPresenter {

    private MainActivityView mainActivityView;

    private int currentPage = 1;

    public Boolean filterApplied;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    public void setFilterApplied(Boolean filterApplied) {
        this.filterApplied = filterApplied;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * This method is responsible for calling the Popular Movies BAL ( Business Access Layer ) to call the popular Movies API.
     * This method handles the success and failure cases specific to the network call.
     */
    public void performMoviesFetch(){

        PopularMoviesBAL.getPopularMovies(currentPage, new MoviesFetchListener() {
            /**
             * This method is called when the BAL successfully fetches the movies list.
             * @param response contains the response from the Popular Movies API.
             */
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                currentPage = response.getPage();

                mainActivityView.onMoviesFetched(response);

            }

            /**
             * This method is called when the BAL is unable to call get the response from the server. Or there is some other exception or error
             */
            @Override
            public void onMoviesNotFetched() {
                mainActivityView.onMoviesFetchedFailed();
            }

            /**
             * This method is called when the BAL finds out that there is no internet connection.
             */
            @Override
            public void onNetworkFailure() {
                mainActivityView.onNetworkFailure();
            }
        });

    }

    /**
     * This method is responsible for calling the BAL again when the user reaches the end of recycler view.
     * In this method the Main Recycler View implements the onScrollListener and its respective functionality.
     * When more movie items are fetched successfully then the main Activity is being notified by the listener.
     * @param mainRecycler contains the instance of mainRecycler from Main Activity.
     * @param swipeRefresh contains the instance if swipeRefresh from Main Activity.
     * @param mLayoutManager contains the instance if mLayoutManager from Main Activity.
     */
    public void loadMoreMovieItems(RecyclerView mainRecycler, final SwipeRefreshLayout swipeRefresh, final LinearLayoutManager mLayoutManager){

        mainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                // Check that if the previous api call is still in progress.
                if (!swipeRefresh.isRefreshing() && !filterApplied) {
                    // Check that if the user reaches the end of scroll.
                    if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount)
                    {
                        PopularMoviesBAL.getPopularMovies(++currentPage, new MoviesFetchListener() {

                            /**
                             * This method is called when the BAL successfully fetches the movies list.
                             * @param response contains the response from the Popular Movies API.
                             */
                            @Override
                            public void onMoviesFetched(MoviesResponse response) {

                                currentPage = response.getPage();

                                mainActivityView.onPagination(response);

                            }

                            /**
                             * This method is called when the BAL is unable to call get the response from the server. Or there is some other exception or error
                             */
                            @Override
                            public void onMoviesNotFetched() {
                                mainActivityView.onMoviesFetchedFailed();
                            }

                            /**
                             * This method is called when the BAL finds out that there is no internet connection.
                             */
                            @Override
                            public void onNetworkFailure() {
                                mainActivityView.onNetworkFailure();
                            }
                        });
                    }
                }
            }
        });

    }

    public void onSwipeRefresh(SwipeRefreshLayout swipeRefreshLayout){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                filterApplied = false;

                currentPage = 1;

                performMoviesFetch();

            }
        });

    }
}
