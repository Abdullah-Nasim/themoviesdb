package myown.themoviesdb.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import myown.themoviesdb.R;
import myown.themoviesdb.activities.adapters.MainRecyclerAdapter;

/**
 * Created by Netaq on 10/5/2017.
 *
 * The Main Activity features the list of popular movies from the movie db API.
 * This class contains the implementation of recycler view which follows gird layout structure to display movies.
 * Pagination and Infinite Scroll is also implemented in this class.
 *
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.main_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private int currentPage = 1;

    private LinearLayoutManager mLayoutManager;

    private MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
