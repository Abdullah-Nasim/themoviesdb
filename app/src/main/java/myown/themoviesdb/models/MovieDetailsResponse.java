package myown.themoviesdb.models;

import java.util.List;

/**
 * Created by Abdullah on 9/14/2017.
 *
 * This class features the response model of Movie Details API.
 */



public class MovieDetailsResponse {

    private boolean adult;
    private String backdrop_path;
    private BelongsToCollection belongs_to_collection;
    private Integer budget;
    private List<Genre> genres;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private List<ProductionCompany> production_companies;
    private List<ProductionCountry> production_countries;
    private String release_date;
    private long revenue;
    private int runtime;
    private List<SpokenLanguage> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public Integer getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public String getHomepage() {
        return homepage;
    }

    private class BelongsToCollection
    {
        private int id;
        private String name;
        private String poster_path;
        private String backdrop_path;
    }

    public class Genre
    {
        private int id;
        private String name;

        public String getName() {
            return name;
        }
    }

    private class ProductionCompany
    {
        private String name;
        private int id;
    }

    private class ProductionCountry
    {
        private String iso_3166_1;
        private String name;
    }

    public class SpokenLanguage
    {
        private String iso_639_1;
        private String name;
    }

}

