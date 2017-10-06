package myown.themoviesdb.activities.filter_activity;


import java.util.ArrayList;
import java.util.Collections;

import myown.themoviesdb.interfaces.filter_activity.FilterActivityView;
import myown.themoviesdb.models.MoviesResponse;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This is the presenter class for the Filter Activity.
 * All of the functionality and app logic about the Filter Activity is implemented in this class.
 * This class notifies the listeners after some event is completed.
 */

public class FilterActivityPresenter {

    private FilterActivityView filterActivityView;

    public FilterActivityPresenter(FilterActivityView filterActivityView) {
        this.filterActivityView = filterActivityView;
    }

    /**
     * This method is responsible for sorting the releasing years of all movies in moviesArray.
     * @param moviesArray contains the list of unsorted movies in the same form received from the API.
     */
    public void getSortedYearsList(ArrayList<MoviesResponse.Result> moviesArray){

        ArrayList<String> yearsListUnsorted = new ArrayList<>();

        ArrayList<String> yearSorted = new ArrayList<>();

        // Make the list which contains the list of years just. Splitting from day and month.
        for(MoviesResponse.Result movie : moviesArray){
            yearsListUnsorted.add(movie.getRelease_date().substring(0,movie.getRelease_date().indexOf('-')));
        }

        // Getting min value from the list of years.
        String min = Collections.min(yearsListUnsorted);

        // Getting max value from the list of years.
        String max = Collections.max(yearsListUnsorted);

        // Making the suitable list of years which will ensure at least one result on filter apply.
        for(Integer i = Integer.parseInt(min); i<=Integer.parseInt(max); i++){
            yearSorted.add(i.toString());
        }

        filterActivityView.onYearsListSorted(yearSorted);

    }

    /**
     * This method is responsible for creating a filtered on the basis of min and max value selected by the user.
     * @param moviesArray contains the list of unsorted movies in the same form as received from the API.
     * @param min is the min year value selected by the user to apply filter.
     * @param max is the max year value selected by the user to apply filter.
     */
    public void applyFilterMoviesDataSet(ArrayList<MoviesResponse.Result> moviesArray, String min, String max){

        ArrayList<MoviesResponse.Result> filteredMovieArray = new ArrayList<>();

        // Filtering out all of the movies which do not lies in the specified range. Min and Max

            for (MoviesResponse.Result movie : moviesArray) {

                if (Integer.parseInt(movie.getRelease_date().substring(0, movie.getRelease_date().indexOf('-'))) >= Integer.parseInt(min) && Integer.parseInt(movie.getRelease_date().toString().substring(0, movie.getRelease_date().indexOf('-'))) <= Integer.parseInt(max)) {
                    filteredMovieArray.add(movie);
                }

            }

        filterActivityView.onFilterApplied(filteredMovieArray);

    }
}
