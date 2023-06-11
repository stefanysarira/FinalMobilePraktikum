package com.example.h071211049_finalmobile.networking;

import com.example.h071211049_finalmobile.model.Movie;
import com.example.h071211049_finalmobile.model.TVShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("movie/top_rated")
    Call<DataResponse<List<Movie>>> getMovies(
            @Query("api_key") String apiKey
    );

//    TV Show
    @GET("tv/top_rated")
    Call<DataResponse<List<TVShow>>> getTVShows(
            @Query("api_key") String apiKey
    );
}
