# The Movies DB
The application which uses TMDB api as a base to list down popular movies and allows you to filter them by their release date.

# Libraries Used
 
  *   ButterKnife - For the binding of view components with activities.
  *   Retrofit - For the network call.
  *   Fresco - For images rendering

# Project Structure

  *  com.myown.themoviesdb.activities - Contains all activities
  *  com.myown.themoviesdb.adapters - Contains all custom adapters
  *  com.myown.themoviesdb.models - Contains all our data models
  *  com.myown.themoviesdb.network - Contains all networking code
  *  com.myown.themoviesdb.fragments - Contains all fragments
  *  com.myown.themoviesdb.utils - Contains all helpers supporting code.
  *  com.myown.themoviesdb.interfaces - Contains all interfaces

# Project Description

This project is using TMDB API as a base to list down popular movies. User has option to filter the movies which are already loaded into the application. I had to implement the movies filteration on already loaded dataset because there is no API provided by TMDB for the filteration of movies based on min and max year.

So, essentially when the movies are loaded they are added into one dataset and upon filteration that dataset is being filtered by the application.

Infinite scroll and pagination are one of the silent features of this application. After the filter application pull swipe to refresh in order to unlock pagination

# MVP

The MPV pattren is used to develop this application. In each activity package you can find a presentaion layer for that particular activity. All of the application logic and functionality about any particular activity is implemented in it's respective presentation layer. So, if there is any change in future or some additional functionality we shall be sure about where exactly the change is going to happen in the code.

Using MVP pattren makes this application highly extensible and maintainable.

# Known Issues

After applying the filter user might see the dublication of records in filtered movies list. This issue is because TMDB API returns some duplicated movies on pagination.
