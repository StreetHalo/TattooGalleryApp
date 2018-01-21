package com.example.dale_c.bestappgallery.retrofit;


import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.json.Translate;

import retrofit2.http.GET;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dale_C on 12.11.2017.
 */


public interface APIServiceImages{
    @GET("/api/search/images?")
    Call<ParseGson> getData(@Query("count") int count, @Query("offset") int set, @Query("q") String query);

}
