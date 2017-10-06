package myown.themoviesdb.interfaces.filter_activity;

import java.util.ArrayList;

import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Abdullah on 10/6/2017.
 *
 * This interface is implemented by Filter Activity.
 * Filter Activity implements the respective abstract method to provide the functionality when these events happens.
 */

public interface FilterActivityView {

    void onYearsListSorted(ArrayList<String> yearsArray);

    void onFilterApplied(ArrayList<MoviesResponse.Result> moviesArray);
}
