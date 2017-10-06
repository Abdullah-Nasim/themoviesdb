package myown.themoviesdb.models;

import java.util.List;

/**
 * Created by Netaq on 9/14/2017.
 *
 * This class features the response model of Movie Details API.
 */



public class MovieDetailsResponse {

    public boolean adult;
    public String backdrop_path;
    public BelongsToCollection belongs_to_collection;
    public Integer budget;
    public List<Genre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public String release_date;
    public long revenue;
    public int runtime;
    public List<SpokenLanguage> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Integer getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
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

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
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

    public class BelongsToCollection
    {
        public int id;
        public String name;
        public String poster_path;
        public String backdrop_path;
    }

    public class Genre
    {
        public int id;
        public String name;

        public String getName() {
            return name;
        }
    }

    public class ProductionCompany
    {
        public String name;
        public int id;
    }

    public class ProductionCountry
    {
        public String iso_3166_1;
        public String name;
    }

    public class SpokenLanguage
    {
        public String iso_639_1;
        public String name;
    }

}

