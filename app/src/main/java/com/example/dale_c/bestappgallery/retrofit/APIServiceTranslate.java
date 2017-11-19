package com.example.dale_c.bestappgallery.retrofit;

import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.json.Translate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dale_C on 19.11.2017.
 */

public interface APIServiceTranslate {

    @GET("api/v1.5/tr.json/translate?key=trnsl.1.1.20171112T122004Z.d6558a292b6cf1af.e5e53fd7a64a379dd34002b76ea0702ae4f48e9f")
    Call<Translate> getData(@Query("text") String text, @Query("lang") String lang);
}
