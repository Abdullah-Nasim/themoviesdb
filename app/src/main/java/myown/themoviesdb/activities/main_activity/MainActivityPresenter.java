package myown.themoviesdb.activities.main_activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import myown.themoviesdb.adapters.MainRecyclerAdapter;
import myown.themoviesdb.interfaces.main_activity.MainActivityView;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.network.bals.PopularMoviesBAL;
import myown.themoviesdb.interfaces.MoviesFetchListener;
import myown.themoviesdb.utils.Utils;

/**
 * Created by Netaq on 10/5/2017.
 */

public class MainActivityPresenter {

    private MainActivityView mainActivityView;

    private int currentPage = 1;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    public void performMoviesFetch(){

        PopularMoviesBAL.getPopularMovies(currentPage, new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                currentPage = response.getPage();

                mainActivityView.onMoviesFetched(response);

            }

            @Override
            public void onMoviesNotFetched() {
                mainActivityView.onMoviesFetchedFailed();
            }

            @Override
            public void onNetworkFailure() {
                mainActivityView.onNetworkFailure();
            }
        });

    }

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

                // Check that if the previous api call is still in progress
                if (!swipeRefresh.isRefreshing()) {
                    if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount)
                    {
                        PopularMoviesBAL.getPopularMovies(++currentPage, new MoviesFetchListener() {
                            @Override
                            public void onMoviesFetched(MoviesResponse response) {

                                currentPage = response.getPage();

                                mainActivityView.onPagination(response);

                            }

                            @Override
                            public void onMoviesNotFetched() {
                                mainActivityView.onMoviesFetchedFailed();
                            }

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
}
