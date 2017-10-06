package myown.themoviesdb.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Abdullah on 10/5/2017.
 *
 * This class features the response of Popular Movies API.
 */

public class MoviesResponse {

    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Result> results;

    public int getPage() {
        return page;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public class Result implements Serializable
    {
        private int vote_count;
        private Integer id;
        private boolean video;
        private double vote_average;
        private String title;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private ArrayList<Integer> genre_ids;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getRelease_date() {
            return release_date;
        }

    }

}
