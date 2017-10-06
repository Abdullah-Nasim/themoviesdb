package myown.themoviesdb.activities.filter_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.themoviesdb.Constants;
import myown.themoviesdb.R;
import myown.themoviesdb.activities.main_activity.MainActivity;
import myown.themoviesdb.interfaces.filter_activity.FilterActivityView;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Netaq on 10/6/2017.
 *
 * This activity features the filtration of movies array based on the min and max value of year selected by the user.
 * The presenter class of this activity is responsible for implementing the functionality required for the filtering.
 */

public class FilterActivity extends AppCompatActivity implements FilterActivityView{

    @BindView(R.id.min_year_spinner)
    Spinner minYearSpinner;

    @BindView(R.id.max_year_spinner)
    Spinner maxYearSpinner;

    @BindView(R.id.button_apply)
    Button applyButton;

    private FilterActivityPresenter filterActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        // Initializing the respective presenter. All of the functionality for this Activity can be found in this presenter class.
        filterActivityPresenter = new FilterActivityPresenter(this);

        setUpActivityTasks();

    }

    private void setUpActivityTasks() {

        // Getting the movies array from the intent.
        final ArrayList<MoviesResponse.Result> moviesArray = (ArrayList<MoviesResponse.Result>) getIntent().getSerializableExtra(Constants.UN_FILTERED_MOVIE_ARRAY_KEY);

        // Calling the presenter to start sorting years list.
        filterActivityPresenter.getSortedYearsList(moviesArray);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling the presenter to apply filter on data set.
                filterActivityPresenter.applyFilterMoviesDataSet(moviesArray,minYearSpinner.getSelectedItem().toString(), maxYearSpinner.getSelectedItem().toString());
            }
        });
    }

    /**
     * This method will be called when the sorting of years array is completed.
     * @param yearsArray will contain the sorted list of years for the movies yet loaded.
     */
    @Override
    public void onYearsListSorted(ArrayList<String> yearsArray) {

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearsArray);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Setting up spinners adapters.
        minYearSpinner.setAdapter(spinnerAdapter);

        maxYearSpinner.setAdapter(spinnerAdapter);

    }

    /**
     * This method will be called when the movies list is filtered.
     * @param moviesArray will contain the result of movies which lies between min and max year selected.
     */
    @Override
    public void onFilterApplied(ArrayList<MoviesResponse.Result> moviesArray) {

        // Starting the new Intent of Main Activity. Please note that Main Activity is running in "Single Task" launch mode.
        Intent intent = new Intent(FilterActivity.this, MainActivity.class);
        intent.putExtra(Constants.FILTERED_MOVIE_ARRAY_KEY, moviesArray);
        startActivity(intent);
        finish();

    }
}
